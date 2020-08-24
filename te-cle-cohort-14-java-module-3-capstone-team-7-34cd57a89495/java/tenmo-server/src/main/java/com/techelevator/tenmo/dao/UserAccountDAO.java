package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.TransferLog;
import com.techelevator.tenmo.model.UserAccount;
import com.techelevator.tenmo.models.TransferRequestDTO;

public interface UserAccountDAO {

	public UserAccount getAccount(int id);

	Long getAccountByName(String name);

	public Boolean makeTransfer(TransferRequestDTO transferData);
	
	List<TransferLog> transferHistory(int id);


	public Boolean requestTransfer(TransferLog transferLog);

	List<TransferLog> activeRequests(int id);

	public TransferLog thisRequest(int id);

	public Boolean updateLog(TransferLog transferLog);
	
	
	
}
