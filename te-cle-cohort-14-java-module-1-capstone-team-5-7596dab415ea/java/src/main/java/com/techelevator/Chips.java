package com.techelevator;

public class Chips extends Snack {

	public Chips(String name, String price, int quantity) {
		super(name, price, quantity);
	}

	public String consume() {
		return "\nCrunch, Crunch, Yum!";
	}

}
