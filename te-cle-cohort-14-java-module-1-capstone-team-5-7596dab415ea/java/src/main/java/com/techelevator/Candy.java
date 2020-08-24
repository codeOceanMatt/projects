package com.techelevator;

public class Candy extends Snack {

	public Candy(String name, String price, int quantity) {
		super(name, price, quantity);
	}

	public String consume() {
		return "\nMunch, Munch, Yum!";
	}

}
