package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.UserAccountDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.TransferLog;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserAccount;
import com.techelevator.tenmo.models.TransferRequestDTO;

@RestController
@PreAuthorize("isAuthenticated()")

public class AccountController {
	
	private UserAccountDAO accountDao;
	private UserDAO userDao;
	
	public AccountController(UserAccountDAO accountDao, UserDAO userDao) {
		this.accountDao = accountDao;
		this.userDao = userDao;
	}
	
	
	
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET) 
	public UserAccount getAccount(@PathVariable int id) throws UsernameNotFoundException {
		UserAccount anAccount = accountDao.getAccount(id);
		return anAccount;
	}
	
	
	@RequestMapping(value = "/findallusers/{id}", method = RequestMethod.GET)
	public List<User> getListTargetAccts(@PathVariable int id) {
		
		List<User> listOfUsers = userDao.findAllButMe(id);
		
		return listOfUsers;
	}
	
	@RequestMapping(value = "/make_transfer", method = RequestMethod.PUT)
	public Boolean initiateTransfer(@RequestBody TransferRequestDTO transferData) {
		Boolean isApproved = false;
		isApproved = accountDao.makeTransfer(transferData);
		return isApproved;
	}
	
	@RequestMapping(value = "/get_history/{id}", method = RequestMethod.GET)
	public List<TransferLog> getTransferHistory(@PathVariable int id){
	List<TransferLog> history = accountDao.transferHistory(id);
	return history;
	}
	
	@RequestMapping(value = "/get_name/{id}", method = RequestMethod.GET)
	public String getName(@PathVariable int id) {
		String name = userDao.findNameById(id);
		return name;
	}
	
	@RequestMapping(value = "/request_bucks", method = RequestMethod.POST)
	public Boolean requestTransfer(@RequestBody TransferLog transferLog) {
		Boolean requestApproved=false;
		requestApproved= accountDao.requestTransfer(transferLog);
		return requestApproved;
	}
	
	@RequestMapping(value = "/get_requests/{id}", method = RequestMethod.GET)
	public List<TransferLog> getPendingRequests(@PathVariable int id){
	List<TransferLog> history = accountDao.activeRequests(id);
	return history;
	}
	
	@RequestMapping(value = "/request_reply/{id}", method = RequestMethod.GET)
	public TransferLog getThisRequest(@PathVariable int id){
	TransferLog history = accountDao.thisRequest(id);
	return history;
	}
	
	@RequestMapping(value = "/update_log", method = RequestMethod.PUT)
	public Boolean updateLog(@RequestBody TransferLog transferLog) {
		Boolean requestApproved=false;
		requestApproved= accountDao.updateLog(transferLog);
		return requestApproved;
	}
	

}
