package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Recipe;

@Service
public class RecipeSqlDAO implements RecipeDAO {
	
	private JdbcTemplate jdbcTemplate;

    public RecipeSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	

	@Override
	public Recipe createRecipe(String name, String instructions) {

		String sqlAddRecipe = "INSERT INTO recipes (recipe_id,recipe_name,instructions) VALUES(?,?,?)";
		SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT nextval('recipes_recipe_id_seq')");
		result.next();
		Long recipeId = result.getLong(1);
		jdbcTemplate.update(sqlAddRecipe,recipeId,name,instructions);
		Recipe recipe = new Recipe(recipeId, name, instructions);
			
		return recipe;
	
	}
	
	
	
	// Add to relational table to connect recipe and user
	public void addRecipeToUser(Recipe recipe, int userId) {
		int recipeId = recipe.getRecipeId().intValue();
//	int userId = (int) getRecipeIdByName(username);
	String sqlAddRecipeToUser = "INSERT INTO user_recipes (user_id, recipe_id) VALUES (?, ?)";
	jdbcTemplate.update(sqlAddRecipeToUser,userId, recipeId);
	
	}
	
	// RETURNING A LIST OF RECIPES (SQL STATEMENT SUBJECT TO CHANGE)
	public List<Recipe> getAllRecipesByUserId(Long id) {
		List<Recipe> recipeList = new ArrayList<>();
		 String sql = "select recipes.recipe_id, recipes.recipe_name, recipes.instructions \n" + 
		 		"from recipes \n" + 
		 		"join user_recipes on user_recipes.recipe_id = recipes.recipe_id\n" + 
		 		"join users on users.user_id = user_recipes.user_id\n" + 
		 		"where users.user_id = ?;";
		 SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
		 while(results.next()) {
			 Recipe recipe = mapRowToRecipe(results);
			 recipeList.add(recipe);
		 }
		return recipeList;
	}


	public long getRecipeIdByName(String recipeName) {
		long recipeId;
		String sqlGetRecipeId = "SELECT recipe_id FROM recipes WHERE recipe_name = ?";
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetRecipeId, recipeName);
			result.next();
			recipeId = result.getLong(1);		

		return recipeId;
		
	}
	
	public void updateRecipeInstructions(String recipeInstructions, long recipeId) {
		String sqlUpdateInstructions = "UPDATE recipes " + 
				"SET instructions = ? WHERE recipe_id = ?"; 
		jdbcTemplate.update(sqlUpdateInstructions, recipeInstructions, recipeId);
				
	}
	
	public Recipe getRecipeByRecipeId(long recipeId) {
		Recipe aRecipe = new Recipe();
		String sqlGetRecipe = "SELECT *" + 
				"FROM recipes\n" + 
				"WHERE recipe_id = ?";
		 SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetRecipe, recipeId);
		 while(results.next()) {
			 aRecipe = mapRowToRecipe(results);
		 }
		return aRecipe;
	}
	
	// To get recipe info from database
	private Recipe mapRowToRecipe(SqlRowSet resultsFromSQL) {
		Recipe aRecipe = new Recipe();
		
		aRecipe.setRecipeId(resultsFromSQL.getLong("recipe_id"));
		aRecipe.setRecipeName(resultsFromSQL.getString("recipe_name"));
		aRecipe.setRecipeInstructions(resultsFromSQL.getString("instructions"));
//		aRecipe.setMealCategoryId(resultsFromSQL.getInt("meal_category_id"));
		
		return aRecipe;
	}
	
	
	
	
//	
//	// to get just recipeId
//	private Long mapRowToId(SqlRowSet resultsFromSQL) {
//		Long recipeId = resultsFromSQL.getLong("recipe_id");
//		return recipeId;
//	}
	
	
}
