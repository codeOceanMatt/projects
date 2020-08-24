package com.techelevator.model;

public class MealPlan {

	private Long planId;
	private int userId;
	private String planName;
	
	public MealPlan(Long planId, int userId, String planName) {
		this.planId = planId;
		this.userId = userId;
		this.planName = planName;
	}
	
	public MealPlan() {
		
	}
	
	public int getUserId() {
		return userId;
	}
	
	public Long getPlanId() {
		return planId;
	}
	
	public void setPlanId(Long planId) {
		this.planId = planId;
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
