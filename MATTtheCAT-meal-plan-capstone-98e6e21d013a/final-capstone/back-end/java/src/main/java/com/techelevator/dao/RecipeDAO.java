package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Recipe;

public interface RecipeDAO {
	
	 Recipe createRecipe(String name, String instructions);
	
	 public void addRecipeToUser(Recipe recipe, int userId);
	 
	 public List<Recipe> getAllRecipesByUserId(Long id);
	 
	 public long getRecipeIdByName(String recipeName); 
	 
	 public void updateRecipeInstructions(String recipeInstructions, long recipeId);
	 
	 public Recipe getRecipeByRecipeId(long recipeId);
	 


}
