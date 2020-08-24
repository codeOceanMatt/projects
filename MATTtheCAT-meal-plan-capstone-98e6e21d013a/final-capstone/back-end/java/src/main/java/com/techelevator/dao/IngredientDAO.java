package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Ingredient;

public interface IngredientDAO {
	
	
	public void addIngredientToIngredientList(Ingredient[] ingredients);
	
	public void addIngredientToRecipeIngredients(Ingredient[] ingredients, int recipeId);
	
	public long getIngredientIdByName(String ingredientName);
	
	public void deleteIngredientFromRecipe(int recipeId, String name);
	
	public List<Ingredient> getIngredientsByRecipeId(long recipeId);

}
