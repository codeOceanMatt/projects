package com.techelevator.model;

public class RecipeDTO {

	
	private String username;
	private Long recipeId;
	private String recipeName;
	private Ingredient [] ingredients;
	private String recipeInstructions;
	
	public RecipeDTO() {
		
	}
	public RecipeDTO(String username, Long recipeId,String recipeName,Ingredient [] ingredients,String recipeInstructions) {
		this.username=username;
		this.recipeId=recipeId;
		this.recipeName=recipeName;
		this.ingredients= ingredients;
		this.recipeInstructions=recipeInstructions;
//		this.mealCategoryId=mealCategoryId;
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
	public Ingredient[] getIngredients() {
		return ingredients;
	}
	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
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
//	public void setMealCategoryId(int mealCategoryId) {
//		this.mealCategoryId = mealCategoryId;
//	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	



}
