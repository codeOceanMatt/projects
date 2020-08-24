package com.techelevator.model;

public class MealPlanDTO {
	
	
	private int userId;
	private String planName;
	private MealPlanRecipe [] mealPlanRecipes;
	
	public MealPlanRecipe[] getMealPlanRecipes() {
		return mealPlanRecipes;
	}

	public void setMealPlanRecipes(MealPlanRecipe[] mealPlanRecipes) {
		this.mealPlanRecipes = mealPlanRecipes;
	}

	public MealPlanDTO() {
		
	}
	
	public MealPlanDTO(int userId, String planName) {
		
		this.userId = userId;
		this.planName = planName;
	}
	
	public int getUserId() {
		return userId;
	}
	

	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}


	
	

}
