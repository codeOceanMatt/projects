package com.techelevator.model;

public class AddIngredientDTO {

	private Long recipeId;
	private String recipeInstructions;
	private String recipeName;
	private Ingredient [] ingredients;
	
	
	public AddIngredientDTO(Long recipeId, String recipeInstructions,String recipeName,Ingredient[] ingredients) {
		this.recipeId=recipeId;
		this.recipeInstructions=recipeInstructions;
		this.recipeName=recipeName;
		this.ingredients=ingredients;
	}
	
	public Long getRecipeId() {
		return recipeId;
	}


	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}


	public String getRecipeInstructions() {
		return recipeInstructions;
	}


	public void setRecipeInstructions(String recipeInstructions) {
		this.recipeInstructions = recipeInstructions;
	}


	public String getRecipeName() {
		return recipeName;
	}


	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public Ingredient[] getIngredients() {
		return ingredients;
	}

	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}


	
	
	
	
}
