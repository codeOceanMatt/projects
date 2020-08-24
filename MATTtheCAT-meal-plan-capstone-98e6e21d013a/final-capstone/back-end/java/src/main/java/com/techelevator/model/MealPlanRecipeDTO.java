package com.techelevator.model;

public class MealPlanRecipeDTO {
	
//	private Long id;
	private int recipeId;
//	private int mealPlanId;
	private String mealCategory;
	private String dayOfWeek;
	
	public MealPlanRecipeDTO ( int recipeId, String mealCategory, String dayOfWeek) {
//		this.id = id;
		this.recipeId = recipeId;
//		this.mealPlanId = mealPlanId;
		this.mealCategory = mealCategory;
		this.dayOfWeek = dayOfWeek;
	}
	
	public MealPlanRecipeDTO() {
		
	}

	

	public int getRecipeId() {
		return recipeId;
	}

	

	public String getMealCategory() {
		return mealCategory;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	

	public void setMealCategory(String mealCategory) {
		this.mealCategory = mealCategory;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	

}
