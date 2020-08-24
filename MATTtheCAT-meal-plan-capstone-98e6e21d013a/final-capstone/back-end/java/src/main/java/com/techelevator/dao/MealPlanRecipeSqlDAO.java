package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.MealPlanRecipe;
@Service
public class MealPlanRecipeSqlDAO implements MealPlanRecipeDAO {
	
	private JdbcTemplate jdbcTemplate;

	public MealPlanRecipeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void addRecipeToMealPlanRecipe (MealPlanRecipe recipe) {
		String sqlAddRecipeToMealPlanRecipe = "INSERT into meal_plan_recipe "
				+ "(recipe_id, meal_plan_id, meal_category, day_of_week) "
				+ "VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddRecipeToMealPlanRecipe, recipe.getId(), recipe.getMealPlanId(), recipe.getMealCategory(), recipe.getDayOfWeek());
		
	}
	public void addRecipesToMealPlanRecipe(MealPlanRecipe [] recipes, int id) {
		String sqlAddRecipeToMeal = "INSERT INTO meal_plan_recipe (recipe_id, meal_plan_id, meal_category, day_of_week)" + " VALUES (?,?,?,?)";

		for (int i = 0; i < recipes.length; i++) {
			 jdbcTemplate.update(sqlAddRecipeToMeal, recipes[i].getRecipeId(),id,recipes[i].getMealCategory(),recipes[i].getDayOfWeek());

			

		}
	}
	
	public List<MealPlanRecipe> getRecipesFromMealPlanRecipe(int mealPlanId) {
		List<MealPlanRecipe> mealPlanRecipes = new ArrayList<>();
		MealPlanRecipe aMealPlanRecipe = new MealPlanRecipe();
		String sqlGetRecipes = "SELECT * FROM meal_plan_recipe WHERE meal_plan_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetRecipes, mealPlanId);
		results.next();
		while(results.next()) {
			aMealPlanRecipe = mapRowToMealPlanRecipe(results);
			mealPlanRecipes.add(aMealPlanRecipe);
		}
		
		return mealPlanRecipes;
	}
	

	private MealPlanRecipe mapRowToMealPlanRecipe(SqlRowSet resultsFromMealPlanRecipeSQL) {
		MealPlanRecipe aRecipe = new MealPlanRecipe();
		
		aRecipe.setId(resultsFromMealPlanRecipeSQL.getLong("id"));
		aRecipe.setRecipeId(resultsFromMealPlanRecipeSQL.getInt("recipe_id"));
		aRecipe.setMealPlanId(resultsFromMealPlanRecipeSQL.getInt("meal_plan_id"));
		aRecipe.setMealCategory(resultsFromMealPlanRecipeSQL.getString("meal_category"));
		aRecipe.setDayOfWeek(resultsFromMealPlanRecipeSQL.getString("day_of_week"));
		
		return aRecipe;
	}

}
