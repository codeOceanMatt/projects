package com.techelevator;

public class Gum extends Snack {

	public Gum(String name, String price, int quantity) {
		super(name, price, quantity);
	}

	public String consume() {
		return "\nChew, Chew, Yum!";
	}

}
