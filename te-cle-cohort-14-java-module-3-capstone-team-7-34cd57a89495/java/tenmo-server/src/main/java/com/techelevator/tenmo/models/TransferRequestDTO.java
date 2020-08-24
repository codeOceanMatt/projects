package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class TransferRequestDTO {
	private int transferToId;
	private int transferFromId;
	private BigDecimal amountToTransfer;
	
	public TransferRequestDTO(int transferToId, int transferFromId, BigDecimal amountToTransfer) {
		this.transferToId = transferToId;
		this.transferFromId = transferFromId;
		this.amountToTransfer = amountToTransfer;
	}

	/**
	 * @return the transferToId
	 */
	public int getTransferToId() {
		return transferToId;
	}

	/**
	 * @param transferToId the transferToId to set
	 */
	public void setTransferToId(int transferToId) {
		this.transferToId = transferToId;
	}

	/**
	 * @return the transferFromId
	 */
	public int getTransferFromId() {
		return transferFromId;
	}

	/**
	 * @param transferFromId the transferFromId to set
	 */
	public void setTransferFromId(int transferFromId) {
		this.transferFromId = transferFromId;
	}

	/**
	 * @return the amountToTransfer
	 */
	public BigDecimal getAmountToTransfer() {
		return amountToTransfer;
	}

	/**
	 * @param amountToTransfer the amountToTransfer to set
	 */
	public void setAmountToTransfer(BigDecimal amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}
}
