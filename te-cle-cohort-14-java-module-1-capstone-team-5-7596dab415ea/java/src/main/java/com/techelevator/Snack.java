package com.techelevator;

public abstract class Snack {

	private String name;
	private String price;
	private int quantity;

	public Snack(String name, String price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;

	}

	public int getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return name + " " + price + " quantity " + quantity;
	}

	public int removeOne() {

		return quantity -= 1;
	}

	public abstract String consume();

}
