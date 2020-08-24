package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.TransferLog;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserAccount;
import com.techelevator.tenmo.models.TransferRequestDTO;

@Service
public class UserAccountSqlDAO implements UserAccountDAO {
	
	private JdbcTemplate jdbcTemplate;

    public UserAccountSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public UserAccount getAccount(int id) {
    	Long userId = new Long(id);
    	UserAccount userAccount = null;
    	String accountBalance = "SELECT * FROM accounts WHERE user_id = ?";
    	SqlRowSet results = jdbcTemplate.queryForRowSet(accountBalance, userId);
    	while(results.next()) {
    	userAccount = mapRowToUserAccount(results);
    	}
    	
    	return userAccount;
    }
    
    @Override
    public Long getAccountByName(String name) {
    	Long userId=0L;
    	String accountBalance = "SELECT user_id FROM users WHERE username = ?";
    	SqlRowSet results = jdbcTemplate.queryForRowSet(accountBalance, name);
    	while(results.next()) {
    	userId = results.getLong("user_id");
    	}
    	return userId;
    }
    
    @Override
    public Boolean makeTransfer(TransferRequestDTO transferData) {
    	Boolean isApproved = true;
    	
    	String sendATransfer = "UPDATE accounts SET balance = (SELECT balance FROM accounts WHERE user_id = ?) - ?" + 
    			" WHERE user_id = ?";
    	jdbcTemplate.update(sendATransfer, transferData.getTransferFromId(),
    	 transferData.getAmountToTransfer(),transferData.getTransferFromId());
    			
    	String takeATransfer = "UPDATE accounts SET balance = (SELECT balance FROM accounts WHERE user_id = ?) + ?" + 
    			" WHERE user_id = ?";
    	jdbcTemplate.update(takeATransfer, transferData.getTransferToId(),
    	 transferData.getAmountToTransfer(),transferData.getTransferToId());
    	
    	
    	String updateTransfer = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)" 
    						  + " VALUES (2, 2, ?, ?, ?)";
    	jdbcTemplate.update(updateTransfer, transferData.getTransferFromId(), 
    	 transferData.getTransferToId(), transferData.getAmountToTransfer());
    		
    	return isApproved;
    	
    }
    
    @Override
    public List<TransferLog> transferHistory(int id){
    	List<TransferLog> history = new ArrayList<>();
    	
    	String getHistory = "SELECT * FROM transfers WHERE account_from = ? OR account_to = ?";
    	SqlRowSet results = jdbcTemplate.queryForRowSet(getHistory, id, id);
    	while(results.next()) {
    		TransferLog transfer = mapRowToLog(results);
            history.add(transfer);
    	}
    	
    	return history;
    }
    @Override
    public Boolean requestTransfer(TransferLog transferLog) {
    	Boolean requestSent=true;
    	String sqlRequestTransfer="INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount)"
    			+ "					VALUES (1,1,?,?,?)";
    	 jdbcTemplate.update(sqlRequestTransfer, transferLog.getAccountFrom(), transferLog.getAccountTo(),transferLog.getAmount());
    	return requestSent;
    }
    
    @Override
    public List<TransferLog> activeRequests(int id){
    	List<TransferLog> history = new ArrayList<>();
    	
    	String getHistory = "SELECT * FROM transfers WHERE (account_from = ? or account_to = ?) AND transfer_status_id = 1";
    	SqlRowSet results = jdbcTemplate.queryForRowSet(getHistory, id, id);
    	while(results.next()) {
    		TransferLog transfer = mapRowToLog(results);
            history.add(transfer);
    	}
    	
    	return history;
    }
    
    @Override
    public TransferLog thisRequest(int id) {
    	TransferLog thisRequest = new TransferLog();
    	
    	String getRequest = "SELECT * FROM transfers WHERE transfer_id = ?";
    	SqlRowSet results = jdbcTemplate.queryForRowSet(getRequest, id);
    	while(results.next()) {
    		thisRequest = mapRowToLog(results);
            
    	}
    	
    	return thisRequest;
    	
    }
    
    @Override
    public Boolean updateLog(TransferLog transferLog) {
    	Boolean requestSent=true;
    	String sqlRequestTransfer="UPDATE transfers SET transfer_status_id = ? WHERE transfer_id = ?";
    			
    	 jdbcTemplate.update(sqlRequestTransfer,transferLog.getTransferStatusId(), transferLog.getTransferId());
    	return requestSent;
    }
///////////////////////////////////////////////////////////////||||||||||||||||||||\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
////////////////////////////////////////////////////////////// HELPER METHODS BELOW \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ |||||||||||||||||||| ////////////////////////////////////////////////////////////////////////////////
///\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\||||||||||||||||||||////////////////////////////////////////////////////////////////////////////////    
    
    private UserAccount mapRowToUserAccount(SqlRowSet results) {
    	UserAccount userAccount = new UserAccount(results.getInt("user_id"),
    			results.getLong("account_id"),results.getBigDecimal("balance"));
    	return userAccount;
    }
    
    private TransferLog mapRowToLog(SqlRowSet results) {
    	TransferLog history = new TransferLog(results.getLong("transfer_id"),results.getInt("transfer_type_id")
    			                           ,results.getInt("transfer_status_id"),results.getInt("account_from")
    			                                 ,results.getInt("account_to"),results.getBigDecimal("amount"));
    	return history;
    }
    

}
