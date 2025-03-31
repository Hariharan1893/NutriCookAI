package com.nutricookai.dto;

import java.util.List;

public class RecipeRequest {
    private List<String> ingredients;
    private String cuisine;
    private String diet;

    public RecipeRequest(List<String> ingredients, String cuisine, String diet) {
        this.ingredients = ingredients;
        this.cuisine = cuisine;
        this.diet = diet;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }
}
