package com.techelevator.model;

public class Recipe {

	private Long recipeId;
	private String recipeName;
	private String recipeInstructions;
//	private int mealCategoryId;
	
	public Recipe(Long recipeId, String recipeName, String recipeInstructions) {
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.recipeInstructions = recipeInstructions;
//		this.mealCategoryId = mealCategoryId;
	}
	
	public Recipe() {
		
	}

	public Long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getRecipeInstructions() {
		return recipeInstructions;
	}

	public void setRecipeInstructions(String recipeInstructions) {
		this.recipeInstructions = recipeInstructions;
	}

//	public int getMealCategoryId() {
//		return mealCategoryId;
//	}
//
//	public void setMealCategoryId(int mealCategoryId) {
//		this.mealCategoryId = mealCategoryId;
//	}
	

}
