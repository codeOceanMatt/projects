package com.techelevator.tenmo.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.TransferLog;
import com.techelevator.tenmo.models.TransferRequestDTO;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserAccount;



public class AccountService {

	public static String AUTH_TOKEN = "";
	private String BASE_URL;
	private RestTemplate restTemplate = new RestTemplate();

	public AccountService(String url) {
		this.BASE_URL = url;
	}

	public UserAccount getAccount(int id) throws AuthenticationServiceException {
		
		UserAccount thisAccount = null;

		thisAccount = restTemplate.exchange(BASE_URL+"accounts/"+id,HttpMethod.GET,makeAuthEntity(), UserAccount.class).getBody();
			
			return thisAccount;
	}
	
	public User[]  getListTargetAccts(int id)throws AuthenticationServiceException {
		 ResponseEntity<User[]> listOfAccounts = restTemplate.exchange(BASE_URL+"findallusers/"+id, 
				 HttpMethod.GET,makeAuthEntity(),User[].class); 
		
		return listOfAccounts.getBody();
	}
	
	public Boolean initiateTransfer(TransferRequestDTO transferData) {
		
		ResponseEntity<Boolean> isApproved = restTemplate.exchange(BASE_URL + "make_transfer", 
				HttpMethod.PUT, makeAuthEntityForTransfer(transferData),Boolean.class);
				
		return isApproved.getBody();
	}
	
	public TransferLog[] getTransferHistory(int id)throws AuthenticationServiceException{
		
		ResponseEntity<TransferLog[]> history = restTemplate.exchange(BASE_URL + "get_history/" + id,
				HttpMethod.GET, makeAuthEntity(), TransferLog[].class);
		
		return history.getBody();
	}
	
	public String getNameFromUserId(int id) {
		
		ResponseEntity<String> name = restTemplate.exchange(BASE_URL + "get_name/" + id, HttpMethod.GET, makeAuthEntity(), String.class);
		
		return name.getBody();		
	}
	
	public Boolean addTransferRequest(TransferLog transferLog) {
		ResponseEntity<Boolean> addTransfer= restTemplate.exchange(BASE_URL+"request_bucks", HttpMethod.POST,makeAuthEntityForRequest(transferLog),Boolean.class);
		return addTransfer.getBody();
	}
	
	public TransferLog[] getPendingRequests(int id)throws AuthenticationServiceException{
		
		ResponseEntity<TransferLog[]> history = restTemplate.exchange(BASE_URL + "get_requests/" + id,
				HttpMethod.GET, makeAuthEntity(), TransferLog[].class);
		
		return history.getBody();
	}

	public TransferLog reqestReply(int id)throws AuthenticationServiceException{
	
	ResponseEntity<TransferLog> history = restTemplate.exchange(BASE_URL + "request_reply/" + id,
			HttpMethod.GET, makeAuthEntity(), TransferLog.class);
	
	return history.getBody();
	}
	
	public Boolean updateTransferRequest(TransferLog transferLog) {
		ResponseEntity<Boolean> addTransfer= restTemplate.exchange(BASE_URL+"update_log", HttpMethod.PUT,makeAuthEntityForRequest(transferLog),Boolean.class);
		return addTransfer.getBody();
	}
	
///////////////////////////////////////////////////////////////||||||||||||||||||||\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
////////////////////////////////////////////////////////////// HELPER METHODS BELOW \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ |||||||||||||||||||| ////////////////////////////////////////////////////////////////////////////////
///\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\||||||||||||||||||||////////////////////////////////////////////////////////////////////////////////
	
	private HttpEntity<?> makeAuthEntity() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity<?> entity = new HttpEntity<>(headers);
	    return entity;
	  }
	
	private HttpEntity<?> makeAuthEntityForTransfer(TransferRequestDTO transferData) {	    
		HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity<?> entity = new HttpEntity<>(transferData,headers);
	    return entity;
	  }
	private HttpEntity<?> makeAuthEntityForRequest(TransferLog transferLog) {	    
		HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity<?> entity = new HttpEntity<>(transferLog,headers);
	    return entity;
	  }
	
}
