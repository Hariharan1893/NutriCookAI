package com.nutricookai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutricookai.dto.RecipeRequest;
import com.nutricookai.dto.RecipeResponse;
import com.nutricookai.service.RecipeService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {

	private final RecipeService recipeService;
	
	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@PostMapping
	public Mono<RecipeResponse> getRecipe(@RequestBody RecipeRequest request){
		return recipeService.fetchRecipe(request);
	}
}
