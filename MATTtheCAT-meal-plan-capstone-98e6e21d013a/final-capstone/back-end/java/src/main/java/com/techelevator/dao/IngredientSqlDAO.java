package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Ingredient;

@Service
public class IngredientSqlDAO implements IngredientDAO {

	private JdbcTemplate jdbcTemplate;

	public IngredientSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Check database to see if ingredient is present
	// If yes we need the id
	// If no, add to list and return id
	
	// How do we do map to row if not all parts of an ingredient object are in either table (ingredients & recipe ingredients)

	@Override
	public void addIngredientToIngredientList(Ingredient[] ingredients) {
		String sqlCheckIngredient = "SELECT * FROM ingredients WHERE ingredient_name = ?";
		String sqlAddIngredient = "INSERT INTO ingredients (ingredient_name)" + " VALUES (?)";

		for (int i = 0; i < ingredients.length; i++) {
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCheckIngredient, ingredients[i].getName());

			if (!result.next()) {
				jdbcTemplate.update(sqlAddIngredient, ingredients[i].getName());
			}

		}

	}
	
	public void addIngredientToRecipeIngredients(Ingredient[] ingredients, int recipeId) {
		String sqlCheckIngredient = "SELECT * FROM recipe_ingredients WHERE recipe_id = ? AND ingredient_id = ?";
		String sqlAddIngredient = "INSERT INTO recipe_ingredients (ingredient_id, recipe_id, ingredient_quantity) VALUES"
				+ " (?, ?, ?)";
		for (int i = 0; i < ingredients.length; i++) {
			long ingredientId = getIngredientIdByName(ingredients[i].getName());
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlCheckIngredient, recipeId, ingredientId);
			
			if(!result.next()) {
					
			String quantity = ingredients[i].getQuantity();
			jdbcTemplate.update(sqlAddIngredient, ingredientId, recipeId, quantity);
		}
		}
	}
	
	//If this is broken, comment out lines 44, 47, 49, 51, 56
	
	// Helper method to get ingredientId
	public long getIngredientIdByName(String ingredientName) {
		long ingredientId;
		String sqlGetIngredientId = "SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?";
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetIngredientId, ingredientName);
			result.next();
			ingredientId = result.getLong(1);

		return ingredientId;
		
	}
	
	
	public void deleteIngredientFromRecipe(int recipeId, String name) {
		String sqlDeleteIngredient = "DELETE FROM recipe_ingredients "
				+ "WHERE recipe_id = ? AND ingredient_id = (select ingredient_id from ingredients WHERE ingredient_name = ?)";
		jdbcTemplate.update(sqlDeleteIngredient, recipeId, name);
	}
	
	public List<Ingredient> getIngredientsByRecipeId(long recipeId) {
		List<Ingredient> ingredientsByRecipe = new ArrayList<>();
		Ingredient anIngredient = new Ingredient();
		String sqlGetIngredients = "SELECT ingredients.ingredient_name, recipe_ingredients.ingredient_quantity\n" + 
				"FROM recipe_ingredients\n" + 
				"INNER JOIN recipes ON recipe_ingredients.recipe_id = recipes.recipe_id\n" + 
				"INNER JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id\n" + 
				"WHERE recipes.recipe_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetIngredients, recipeId);
		while(results.next()) {
			anIngredient = mapRowToIngredient(results);
			ingredientsByRecipe.add(anIngredient);
		}
		return ingredientsByRecipe;
		
	}
	

	
	private Ingredient mapRowToIngredient(SqlRowSet resultsFromIngredientsSQL) {
		Ingredient anIngredient = new Ingredient();
		
		anIngredient.setName(resultsFromIngredientsSQL.getString("ingredient_name"));
		anIngredient.setQuantity(resultsFromIngredientsSQL.getString("ingredient_quantity"));	
		
		return anIngredient;
	}

}
