package com.techelevator.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.IngredientDAO;
import com.techelevator.dao.MealPlanDAO;
import com.techelevator.dao.MealPlanRecipeDAO;
import com.techelevator.dao.RecipeDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.AddIngredientDTO;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MealPlan;
import com.techelevator.model.MealPlanDTO;
import com.techelevator.model.MealPlanRecipe;
import com.techelevator.model.MealPlanRecipeDTO;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDTO;

@RestController
@CrossOrigin
public class UserController {
	
	private RecipeDAO recipeDao;
	private IngredientDAO ingredientDao;
	private UserDAO userDao;
	private MealPlanDAO mealPlanDao;
	private MealPlanRecipeDAO mealPlanRecipeDao;
	
	public UserController( RecipeDAO recipeDao, IngredientDAO ingredientDao, UserDAO userDao, MealPlanDAO mealPlanDao, MealPlanRecipeDAO mealPlanRecipeDao) {
		
		this.recipeDao = recipeDao;
		this.ingredientDao = ingredientDao;	
		this.userDao=userDao;
		this.mealPlanDao = mealPlanDao;
		this.mealPlanRecipeDao = mealPlanRecipeDao;
	}

	
// EDIT THIS CREATE RECIPE TO ACCEPT A LONG ID(USER-ID COMING FROM FRONT END)	
@ResponseStatus(HttpStatus.CREATED)
@RequestMapping(path="/createrecipe", method=RequestMethod.POST)
public Recipe addRecipe(@RequestBody RecipeDTO recipeDto) {
	Recipe theRecipe = recipeDao.createRecipe(recipeDto.getRecipeName(),recipeDto.getRecipeInstructions());
	int recipeIdToInt=theRecipe.getRecipeId().intValue();
	String username = recipeDto.getUsername();
	Ingredient [] ingredients = recipeDto.getIngredients();
	ingredientDao.addIngredientToIngredientList(ingredients);
	ingredientDao.addIngredientToRecipeIngredients(ingredients,recipeIdToInt);
	recipeDao.addRecipeToUser(theRecipe,userDao.findIdByUsername(username) ); 
	return theRecipe;
}

@ResponseStatus(HttpStatus.CREATED)
@RequestMapping(path="/createPlan", method=RequestMethod.POST)
public MealPlan createMealPlan(@RequestBody MealPlanDTO mealPlanDto) {
	System.out.println("IS THISSS WORKING!!!!!!!");
	MealPlan theMealPlan = mealPlanDao.createMealPlan(mealPlanDto.getUserId(),mealPlanDto.getPlanName());
	int mealPlanId=theMealPlan.getPlanId().intValue();
	MealPlanRecipe [] recipes = mealPlanDto.getMealPlanRecipes();
	if(recipes.length>0) {
		System.out.println("we have something in there");
	}
	
	mealPlanRecipeDao.addRecipesToMealPlanRecipe(recipes, mealPlanId);
	
	return theMealPlan;
}
@RequestMapping(path="/getMealPlans/{userId}",method=RequestMethod.GET)
public List<MealPlan> getMealPlansByUserId(@PathVariable int userId) {
	return mealPlanDao.getMealPlansByUserId(userId);
}


@RequestMapping(path="/getRecipes/{id}", method=RequestMethod.GET)
public List<Recipe> getRecipes (@PathVariable Long id) {
	System.out.println("/getRecipes/{id} " + id);
	return recipeDao.getAllRecipesByUserId(id);
}

@RequestMapping(path="/getIngredients/{id}",method=RequestMethod.GET)
public List<Ingredient> getIngredients(@PathVariable Long id) {
	return ingredientDao.getIngredientsByRecipeId(id);
}
@RequestMapping(path="/getRecipe/{id}",method=RequestMethod.GET)
public Recipe getRecipe(@PathVariable Long id) {
	return recipeDao.getRecipeByRecipeId(id);
}

@RequestMapping(path="/deleteIngredient/{id}", method=RequestMethod.DELETE)
public void deleteIngredientFromRecipe(@PathVariable int id, @RequestBody Ingredient ingredient) {
	ingredientDao.deleteIngredientFromRecipe(id, ingredient.getName());
}


// method for updating recipe instructions
//@RequestMapping(path="/updateInstructions/{id}", method=RequestMethod.PUT)
//public Recipe updateRecipeInstructions(@PathVariable Long id) {
//	
//}

//@RequestMapping(path="/addRecipeToMealPlan/{planId}", method=RequestMethod.PUT) 
//public void addRecipeToMealPlan(@RequestBody MealPlanRecipeDTO mealPlanRecipeDto) {
//	MealPlanRecipe aMealPlanRecipe = new MealPlanRecipe(mealPlanRecipeDto.getId(), mealPlanRecipeDto.getRecipeId(), mealPlanRecipeDto.getMealPlanId(), mealPlanRecipeDto.getMealCategory(), mealPlanRecipeDto.getDayOfWeek());;
//	mealPlanRecipeDao.addRecipeToMealPlanRecipe(aMealPlanRecipe);
//	
//}

@RequestMapping(path="/addIngredient/{id}",method=RequestMethod.PUT)
public void addIngredientToExistingRecipe(@PathVariable Long id,@RequestBody AddIngredientDTO ingredientDto) {
	System.out.println(ingredientDto.getRecipeName()+"ARE WE REACHING THIS CODE??");
	Ingredient [] newIngredientList = ingredientDto.getIngredients();
	if(newIngredientList.length == 0) {
		System.out.println("Nothing in Array");
	}
	int recipeId= id.intValue();
	
	ingredientDao.addIngredientToIngredientList(newIngredientList);
	ingredientDao.addIngredientToRecipeIngredients(newIngredientList,recipeId);
}
	
	
}
