package com.nutricookai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nutricookai.dto.RecipeRequest;
import com.nutricookai.dto.RecipeResponse;
import com.nutricookai.exception.RecipeGenerationException;

import reactor.core.publisher.Mono;

@Service
public class RecipeService {

	private final WebClient webClient;

	@Value("${gemini.api-key}")
	private String apiKey;
	
	@Value("${gemini.api-url}") 
    private String apiUrl;

	public RecipeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


	@Cacheable(value = "recipes", key = "#request.ingredients")
	public Mono<RecipeResponse> fetchRecipe(RecipeRequest request) {
		String prompt = generatePrompt(request);
		
		String fullUrl = apiUrl + "?key=" + apiKey;

		return webClient.post().uri(fullUrl)
				.bodyValue("{\"contents\": [{\"parts\": [{\"text\": \"" + prompt + "\"}]}]}").retrieve()
				.bodyToMono(String.class).map(this::parseResponse).onErrorResume(
						e -> Mono.error(new RecipeGenerationException("Failed to fetch recipe: " + e.getMessage())));

	}

	private String generatePrompt(RecipeRequest request) {
	    return "Suggest a short and crisp recipe using these ingredients: " + request.getIngredients() +
	           ". Cuisine: " + request.getCuisine() + 
	           ". Diet: " + request.getDiet() + 
	           ". Only provide key details: Recipe name (without 'Recipe Name:' prefix), Ingredients, Simple Steps, and Estimated Calories. " +
	           "Strictly avoid unnecessary introductions, greetings, or long descriptions.";
	}


	


	private RecipeResponse parseResponse(String response) {
		try {
			JSONObject jsonResponse = new JSONObject(response);
			JSONArray candidates = jsonResponse.getJSONArray("candidates");

			if (candidates.isEmpty()) {
				throw new RecipeGenerationException("No recipe found in response.");
			}

			// Extract the recipe text
			JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
			JSONArray parts = content.getJSONArray("parts");
			String fullRecipeText = parts.getJSONObject(0).getString("text");

			return extractRecipeDetails(fullRecipeText);

		} catch (Exception e) {
			throw new RecipeGenerationException("Error parsing recipe response: " + e.getMessage());
		}
	}

	private RecipeResponse extractRecipeDetails(String recipeText) {
	    String[] lines = recipeText.split("\n");
	    String recipeName = lines[0].replaceAll("\\*\\*Recipe Name:\\*\\*", "").trim();
	    List<String> ingredients = new ArrayList<>();
	    List<String> instructions = new ArrayList<>();
	    int calories = 0;

	    boolean ingredientsSection = false;
	    boolean instructionsSection = false;

	    for (String line : lines) {
	        line = line.trim();

	        if (line.toLowerCase().contains("ingredients")) {
	            ingredientsSection = true;
	            instructionsSection = false;
	            continue;
	        }

	        if (line.toLowerCase().contains("instructions") || line.toLowerCase().contains("steps")) {
	            ingredientsSection = false;
	            instructionsSection = true;
	            continue;
	        }

	        if (ingredientsSection && !line.isEmpty()) {
	            ingredients.add(line);
	        }

	        if (instructionsSection && !line.isEmpty()) {
	            instructions.add(line);
	        }

	        if (line.toLowerCase().contains("calories")) {
	            Matcher matcher = Pattern.compile("\\d+").matcher(line);
	            if (matcher.find()) {
	                calories = Integer.parseInt(matcher.group());
	            }
	        }
	    }

	    return new RecipeResponse(recipeName, ingredients, instructions, calories);
	}

}
