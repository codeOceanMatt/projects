package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.MealPlan;

public interface MealPlanDAO {
	
	public MealPlan createMealPlan(int userId, String planName);
	
	public List<Long> getPlanIdsWithUserId(int userId);
	
	public List<MealPlan> getMealPlansByUserId(int userId);

}
