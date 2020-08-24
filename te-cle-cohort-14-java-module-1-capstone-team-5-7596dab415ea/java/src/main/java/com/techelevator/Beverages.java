package com.techelevator;

public class Beverages extends Snack {

	public Beverages(String name, String price, int quantity) {
		super(name, price, quantity);
	}

	public String consume() {
		return "\nGlug, Glug, Yum!";
	}

}
