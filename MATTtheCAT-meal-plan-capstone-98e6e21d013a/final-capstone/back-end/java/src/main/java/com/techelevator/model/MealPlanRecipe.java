package com.techelevator.model;

public class MealPlanRecipe {
	
	private Long id;
	private int recipeId;
	private int mealPlanId;
	private String mealCategory;
	private String dayOfWeek;
	
	public MealPlanRecipe (Long id, int recipeId, int mealPlanId, String mealCategory, String dayOfWeek) {
		this.id = id;
		this.recipeId = recipeId;
		this.mealPlanId = mealPlanId;
		this.mealCategory = mealCategory;
		this.dayOfWeek = dayOfWeek;
	}
	
	public MealPlanRecipe() {
		
	}

	public Long getId() {
		return id;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public int getMealPlanId() {
		return mealPlanId;
	}

	public String getMealCategory() {
		return mealCategory;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public void setMealPlanId(int mealPlanId) {
		this.mealPlanId = mealPlanId;
	}

	public void setMealCategory(String mealCategory) {
		this.mealCategory = mealCategory;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	

}
