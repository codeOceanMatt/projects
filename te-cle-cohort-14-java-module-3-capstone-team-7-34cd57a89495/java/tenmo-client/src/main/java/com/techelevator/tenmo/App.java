package com.techelevator.tenmo;

import java.math.BigDecimal;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.TransferLog;
import com.techelevator.tenmo.models.TransferRequestDTO;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserAccount;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.view.ConsoleService;

public class App {

private static final String API_BASE_URL = "http://localhost:8080/";
    
    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };

	
	
	
	
	
    private AuthenticatedUser currentUser;
    private ConsoleService console;
    private AuthenticationService authenticationService;
    private AccountService accountService;
    private UserAccount thisAccount;
   

    public static void main(String[] args) {
    	App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL),new AccountService(API_BASE_URL));
    	app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService, AccountService accountService) {
		this.console = console;
		this.authenticationService = authenticationService;
		this.accountService=accountService;
		
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");
		
		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() {
		while(true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if(MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if(MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if(MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if(MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if(MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {
//		UserAccount userAccount = getUserIdForAccount();
		try {
		thisAccount = accountService.getAccount(getUserIdForAccount());
		 System.out.println("Your account balance is: " + thisAccount.getBalance() + " - TE Bucks" );
		}catch(AuthenticationServiceException e){
			
			System.out.println("Ya done goofed");
		}
		
		
	}

	private void viewTransferHistory() {
		TransferLog[] history;
		try {
			thisAccount = accountService.getAccount(getUserIdForAccount());
			history = accountService.getTransferHistory(thisAccount.getUserId());
			for(int i = 0; i < history.length; i++) {
				System.out.println("Transaction Id: " + history[i].getTransferId()
						         + "\nTransfer Type: " + history[i].getStringTransferType(history[i].getTransferTypeId())
						         + "\nTransfer Status: " + history[i].getStringTransferStatus(history[i].getTransferStatusId())
						         + "\nSent From: " + accountService.getNameFromUserId(history[i].getAccountFrom())
						         + "\nRecieved By: " + accountService.getNameFromUserId(history[i].getAccountTo())
						         + "\nAmount Transfered: " + history[i].getAmount()
						         + "\n--------------------------------");
			}
		}catch(AuthenticationServiceException e) {
			System.out.println("Ya done goofed");
		}
		
	}

	private void viewPendingRequests() {
		int request = 0;
		String answer = "";
		TransferLog[] history;
		TransferLog thisRequest = new TransferLog();
		boolean isTransfering = true;
		boolean transferApproved = false;
		boolean pendingUpdated = false;
		
		while(isTransfering) {
		boolean canTransfer=true;
		try {
			thisAccount = accountService.getAccount(getUserIdForAccount());
			history = accountService.getPendingRequests(thisAccount.getUserId());
			if(history.length == 0) {
				System.out.println("You have no pending requests.");
				isTransfering = false;
			}else {
			for(int i = 0; i < history.length; i++) {
				System.out.println("Transaction Id: " + history[i].getTransferId()
						         + "\nTransfer Type: " + history[i].getStringTransferType(history[i].getTransferTypeId())
						         + "\nTransfer Status: " + history[i].getStringTransferStatus(history[i].getTransferStatusId())
						         + "\nSent From: " + accountService.getNameFromUserId(history[i].getAccountFrom())
						         + "\nRequest made by: " + accountService.getNameFromUserId(history[i].getAccountTo())
						         + "\nAmount Requested: " + history[i].getAmount()
						         + "\n--------------------------------");
			}
			request = console.getUserInputInteger("\nEnter a Transaction Id to reply to:\nor type '0' to exit");
			if(request==0) {isTransfering=false;}
			for(int i = 0; i < history.length; i++) {
				if(history[i].getTransferId()!=request) {
					canTransfer=false;
					
				}
			}
			if(canTransfer) {
			thisRequest = accountService.reqestReply(request);
			if(thisRequest.getAccountTo() == thisAccount.getUserId() || thisRequest.getAccountFrom() != thisAccount.getUserId()) {
				System.out.println("You can't do that.");
				isTransfering = false;
			} else {
			answer = console.getUserInput("Type approve to grant the request.\nType deny to reject the request.");
			if(answer.equalsIgnoreCase("approve")) {
				TransferRequestDTO transferData = new TransferRequestDTO(thisRequest.getAccountTo(),currentUser.getUser().getId(),thisRequest.getAmount());

				if(thisRequest.getAmount().compareTo(thisAccount.getBalance()) == 1) {
					System.out.println("You're too broke to send that much!");
				} else {
				transferApproved = accountService.initiateTransfer(transferData);
				thisRequest.setTransferStatusId(2);
				pendingUpdated = accountService.updateTransferRequest(thisRequest);
				
				}
				if(transferApproved == true) {
					System.out.println("\nTransfer complete");
				}
				if(pendingUpdated) {
					isTransfering = false;
				}
				
				} else {
					thisRequest.setTransferStatusId(3);
					pendingUpdated = accountService.updateTransferRequest(thisRequest);
					System.out.println("\nRequest denied");
					isTransfering = false;
				}

			}
			}
		}
		}
		catch(AuthenticationServiceException e) {
			System.out.println("Ya done goofed");
		}
		}
		
	}

	private void sendBucks() {	
		
		int transferToId = 0;
		BigDecimal transferAmount;
		Boolean isTransfering = true;
		Boolean transferApproved = false;
		User[] listOfAccounts;
		
		try {
		thisAccount = accountService.getAccount(getUserIdForAccount());
		} catch(AuthenticationServiceException e){
			
			System.out.println("Ya done goofed");
		}
		  
		 while(isTransfering) {
		try {
			listOfAccounts = accountService.getListTargetAccts(getUserIdForAccount());
			System.out.println("Users available to receive transfer:\n");
			 for(int i =0;i<listOfAccounts.length;i++) {
				 System.out.println("Username: "+listOfAccounts[i].getUsername()+ "\nUser-Id: " + listOfAccounts[i].getId() + "\n");
			 }
			 transferToId = console.getUserInputInteger("Please enter the user Id you would like to tranfer to");
				transferAmount = console.getUserInputBigDecimal("How much would you like to transfer ? " 
			                  + "\nPlease use format: ###.## ");
				String thisChoice = console.getUserInput("Type yes to send "+ " $" + transferAmount 
						+ "\nOr type no to go back");
				
				if(thisChoice.equals("yes")) {
				
				TransferRequestDTO transferData = new TransferRequestDTO(transferToId,currentUser.getUser().getId(),transferAmount);

				if(transferAmount.compareTo(thisAccount.getBalance()) == 1) {
					System.out.println("You're too broke to send that much! Try sending less than: "+ thisAccount.getBalance());
				} else {
				transferApproved = accountService.initiateTransfer(transferData);
				
				}
				if(transferApproved == true) {
					System.out.println("Transfer complete");
				}
				isTransfering = false;
				} else {
					isTransfering = false;
				}
			
		} catch (AuthenticationServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 }
		 
	}

	private void requestBucks() {	
	TransferLog transferLog = new TransferLog();
	int requestFromId = 0;
	BigDecimal transferAmount;
	Boolean isTransfering = true;
	Boolean requestSent = false;
	User[] listOfAccounts;
	
	try {
	thisAccount = accountService.getAccount(getUserIdForAccount());
	} catch(AuthenticationServiceException e){
		
		System.out.println("Ya done goofed");
	}
	  
	 while(isTransfering) {
	try {
		listOfAccounts = accountService.getListTargetAccts(getUserIdForAccount());
		System.out.println("Users available to request bucks from:\n");
		 for(int i =0;i<listOfAccounts.length;i++) {
			 System.out.println("Username: "+listOfAccounts[i].getUsername()+ "\nUser-Id: " + listOfAccounts[i].getId() + "\n");
		 }
		 requestFromId = console.getUserInputInteger("Please enter the user Id you would like to request from");
			transferAmount = console.getUserInputBigDecimal("How much would you like to request? " 
		                  + "\nPlease use format: ###.## ");
			transferLog.setAccountTo(thisAccount.getUserId());
			transferLog.setAccountFrom(requestFromId);
			transferLog.setAmount(transferAmount);
			requestSent=accountService.addTransferRequest(transferLog);
			if(requestSent) {
				System.out.println("Request sent");
				isTransfering=false;

			}
		
	} catch (AuthenticationServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 }
	 
}
	
	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while(!isAuthenticated()) {
			String choice = (String)console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
            	authenticationService.register(credentials);
            	isRegistered = true;
            	System.out.println("Registration successful. You can now login.");
            } catch(AuthenticationServiceException e) {
            	System.out.println("REGISTRATION ERROR: "+e.getMessage());
				System.out.println("Please attempt to register again.");
            }
        }
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
		    try {
				currentUser = authenticationService.login(credentials);
				 String token = (String) currentUser.getToken();
                 accountService.AUTH_TOKEN = token;
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}
	
	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
	
	private int getUserIdForAccount() {
		int id = currentUser.getUser().getId();
		return id;
	}
}
