package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.MealPlan;

import com.techelevator.model.Recipe;
@Service
public class MealPlanSqlDAO implements MealPlanDAO {

	private JdbcTemplate jdbcTemplate;

	public MealPlanSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	// Meal plan object has planId and Name
	public MealPlan createMealPlan(int userId, String planName) {

		String sqlAddMealsToMealPlan = "INSERT into meal_plan (user_id, plan_name) " + "VALUES (?, ?)";
		jdbcTemplate.update(sqlAddMealsToMealPlan, userId, planName);
		SqlRowSet result = jdbcTemplate.queryForRowSet("SELECT nextval('meal_plan_plan_id_seq')");
		result.next();
		Long planId = result.getLong(1);
		
	
		MealPlan aMealPlan = new MealPlan(planId-1, userId, planName);
			
		return aMealPlan;

	}

	public List<Long> getPlanIdsWithUserId(int userId) {
		List<Long> planIdsByUser = new ArrayList<>();
		long planId;
		String sqlGetPlanId = "SELECT plan_id FROM meal_plan WHERE user_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetPlanId, userId);
		while (result.next()) {
			planId = result.getLong(1);
			planIdsByUser.add(planId);
			
		}

		return planIdsByUser;
	}
	
	public Long getPlanIdByName(int userId, String planName) {
		long planId;
		String sqlGetPlanId = "SELECT plan_id FROM meal_plan WHERE user_id = ? AND plan_name = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetPlanId, userId, planName);
		result.next();
		planId = result.getLong(1);
		
		return planId;
		
	}
	public List<MealPlan> getMealPlansByUserId(int userId)
	{	List<MealPlan> aListOfMealPlans = new ArrayList<>();
		String sqlGetAllMealPlansById = "SELECT * FROM meal_plan WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllMealPlansById,userId);
		while(results.next()) {
			MealPlan aPlan = mapRowToMealPlan(results);
			aListOfMealPlans.add(aPlan);
		}
		return aListOfMealPlans;
	}
	
	public MealPlan mapRowToMealPlan (SqlRowSet results) {
		MealPlan aPlan = new MealPlan();
		aPlan.setPlanId(results.getLong("plan_id"));
		aPlan.setUserId(results.getInt("user_id"));
		aPlan.setPlanName(results.getString("plan_name"));
		return aPlan;
	}
}
