package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.MealPlanRecipe;

public interface MealPlanRecipeDAO {
	
	public void addRecipeToMealPlanRecipe (MealPlanRecipe recipe);
	
	public List<MealPlanRecipe> getRecipesFromMealPlanRecipe(int mealPlanId);
	
	public void addRecipesToMealPlanRecipe(MealPlanRecipe [] recipes, int id);

}
