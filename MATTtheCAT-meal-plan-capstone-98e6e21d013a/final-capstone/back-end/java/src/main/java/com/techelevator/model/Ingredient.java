package com.techelevator.model;

public class Ingredient {
	
	
	private String name;
	private String quantity;
	
	
	public String getName() {
		return name;
	}
	public String getQuantity() {
		return quantity;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return name + " " + quantity;
	}
	
	
	


}
