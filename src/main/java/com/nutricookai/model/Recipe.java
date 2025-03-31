package com.nutricookai.model;

import java.util.List;

public class Recipe {

	private String recipeName;
	
	private List<String> ingredients;
	
	private List<String> instructions;
	
	private int calories;

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public Recipe(String recipeName, List<String> ingredients, List<String> instructions, int calories) {
		super();
		this.recipeName = recipeName;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.calories = calories;
	}
	
	public Recipe() {
		super();
	}
}
