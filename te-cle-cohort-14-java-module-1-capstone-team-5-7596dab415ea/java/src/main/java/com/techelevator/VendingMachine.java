package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class VendingMachine {

	private double balance;
	public Map<String, Snack> inventory = new TreeMap<>();
	String[] lines;

	String aLine;

	String selectedItem = "";
	int quarters;
	int dimes;
	int nickels;
	int dollars;
	String stringDollar;
	int dollarAmount;
	double itemPrice;
	double balanceBeforeChange;
	String toPrint="";
	String salesReport="";
	
	
	String itemToLog;
	double balanceBeforeSale=0;
	LocalDateTime date = LocalDateTime.now();
	DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("M/dd/yyyy h:mm:ss a");
	DateTimeFormatter fileTime= DateTimeFormatter.ofPattern("M-dd-yyyy h.mm.ss a");
	String dateFile=date.format(fileTime);
	String formattedTime = date.format(dateTime);
	
	
	String feedLog;
		
	Scanner scan = new Scanner(System.in);
	String userChoice;
	NumberFormat amt = NumberFormat.getCurrencyInstance();
	

	public VendingMachine() {
		
	}
	public void generateReport() throws FileNotFoundException {
		File salesFile = new File(dateFile+".txt");
		PrintWriter salesReportToFile= new PrintWriter(salesFile);
		
		double totalForSales=0;
		for(String temp: inventory.keySet()) {
			int totalSold=5-inventory.get(temp).getQuantity();
			salesReport+=inventory.get(temp).getName()+"|"+(totalSold)+"\n";
			totalForSales+=Double.parseDouble(inventory.get(temp).getPrice())*totalSold;
		}
		
		salesReportToFile.println(salesReport);
		salesReportToFile.println("Total Sales: "+amt.format(totalForSales));
		salesReportToFile.close();
		System.out.println("Sales report file was created");
//		System.out.println("Total Sales: "+amt.format(totalForSales));
		
	}

	public void loadInventory() throws FileNotFoundException {
		// read and process text file
		File inputFile = new File("vendingmachine.csv");
		Scanner scanFile = new Scanner(inputFile);
		while (scanFile.hasNextLine()) {
			aLine = scanFile.nextLine();
			lines = aLine.split(",");

			if (lines[0].startsWith("A")) {
				inventory.put(lines[0], new Chips(lines[1], lines[2], 5));
			}
			if (lines[0].startsWith("B")) {
				inventory.put(lines[0], new Candy(lines[1], lines[2], 5));
			}
			if (lines[0].startsWith("C")) {
				inventory.put(lines[0], new Beverages(lines[1], lines[2], 5));
			}
			if (lines[0].startsWith("D")) {
				inventory.put(lines[0], new Gum(lines[1], lines[2], 5));
			}

		}
	}

	public void feedMoney(int dollars) {
		
		this.dollars = dollars;
		balanceBeforeSale+=dollars;
		balance += dollars;

	}

	public String buyItem(){
		balanceBeforeSale=balance;
		userChoice = scan.nextLine();
		userChoice = userChoice.toUpperCase();
		
		if (!inventory.containsKey(userChoice)) {
			System.out.println("Item code invalid");
		} else {

			itemPrice = Double.parseDouble(inventory.get(userChoice).getPrice());

			if (balance < itemPrice) {
				selectedItem="You don't have enough money for that snack.";
			} 
			else {

				if (inventory.get(userChoice).getQuantity() > 0) {
					inventory.get(userChoice).removeOne();

					Snack snack = inventory.get(userChoice);
					selectedItem = snack.toString();
					itemToLog=inventory.get(userChoice).getName();
					

					balance -= itemPrice;
//					balanceBeforeSale-=itemPrice;
					selectedItem += snack.consume();
				} else {
					selectedItem = ("\n Item is SOLD OUT, please pick a different item.");
				}
				try {
					log();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return selectedItem;

	}

	public double getBalance() {
		return balance;
	}

	public void addMoney() {
		System.out.println("Enter valid dollar amount $(1), $(2), $(5), $(10)\n");
		System.out.println("Current Balance: "+amt.format(balance));
		stringDollar = scan.nextLine();
		dollarAmount = Integer.parseInt(stringDollar);
		if (dollarAmount == 1 || dollarAmount == 2 || dollarAmount == 5 || dollarAmount == 10) {
			feedMoney(dollarAmount);
//			toFeed=true;
			try {
				logFeed();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("Invalid dollar amount");
		}
		
			
	}

	public String makeChange() {
		balanceBeforeChange=balance;
		balanceBeforeSale=0;
		quarters = 0;
		dimes = 0;
		nickels = 0;
		while (balance > 0) {
			if (balance >= .25) {
				balance -= .25;
				quarters++;
			} else if (balance >= .1) {
				balance -= .1;
				dimes++;
			} else if (balance >= .05) {
				balance -= .05;
				nickels++;
			} else {
				break;
			}
		}
		return "Your amount of change is " + quarters + " quarters, " + dimes + " dimes,  and " + nickels + " nickels.";

	}
	public void logFeed() throws FileNotFoundException {
		File destinationFile = new File("Log.txt");
		PrintWriter logSales = new PrintWriter(destinationFile);
		Scanner logScan = new Scanner (destinationFile);
		
		
			toPrint+=formattedTime +" FEED MONEY: "  + amt.format(dollarAmount) +" "+ amt.format(balance)+"\n";
		
		
//			toPrint+=formattedTime +" FEED MONEY: "  + amt.format(dollarAmount) +" "+ amt.format(balance);
		
		logSales.println(toPrint);
		logSales.close();
//		formattedTime + "FEED MONEY: " + dollarAmount + balance;
				
	}
	public void logChange() throws FileNotFoundException {
		File destinationFile = new File("Log.txt");
		PrintWriter logSales = new PrintWriter(destinationFile);
		toPrint+=formattedTime +" GIVE CHANGE: "  + amt.format(balanceBeforeChange) +" "+ amt.format(balance)+"\n";
		logSales.println(toPrint);
		logSales.close();
//		formattedTime + "FEED MONEY: " + dollarAmount + balance;
				
	}
	public void log() throws FileNotFoundException{
		
		File destinationFile = new File("Log.txt");
		PrintWriter logSales = new PrintWriter(destinationFile);
//		LocalDateTime date = LocalDateTime.now();
//		DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("mm/dd/yyyy h:mm:ss a");
		formattedTime = date.format(dateTime);
		
//			logSales.print("formattedTime + \"FEED MONEY: \" + dollarAmount + balance");
		
		
		toPrint+=formattedTime + " "+itemToLog +" "+userChoice+" "+amt.format(balanceBeforeSale) +" "+amt.format(balance)+"\n";
		logSales.println(toPrint);
		logSales.close();
//		logSales.         //(formattedTime + selectedItem + balance);
	}
}
