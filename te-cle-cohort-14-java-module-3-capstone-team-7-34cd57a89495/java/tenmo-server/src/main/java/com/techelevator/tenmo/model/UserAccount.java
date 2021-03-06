package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class UserAccount {
	private Integer userId;
	private Long accountId;
	private BigDecimal balance;
	
//	public UserAccount() {}
//	
//	public UserAccount(Integer userId) {
//		this.userId = userId;
//	}
	
	public UserAccount(Integer userId, Long accountId, BigDecimal balance) {
		this.userId = userId;
		this.accountId = accountId;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "UserAccount [userId=" + userId + ", accountId=" + accountId + ", balance=" + balance + "]";
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
