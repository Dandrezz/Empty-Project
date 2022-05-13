package com.montran.exam.transaction;

import java.time.LocalDate;

import com.montran.exam.status.TransactionStatus;

/**
 * This class contains the necessary values to create a new transaction of type RTGS
 * @author Diego Portero
 *
 */
public class TransactionRtgs extends BasicTransaction {
	
	/**
	 * Timestamp the transaction was changed its status
	 */
	private LocalDate changeStatus;
	/**
	 * Transaction status
	 */
	private TransactionStatus status;
	
	// getters and setters
	
	public LocalDate getChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(LocalDate changeStatus) {
		this.changeStatus = changeStatus;
	}
	public TransactionStatus getStatus() {
		return status;
	}
	public void setStatus(TransactionStatus status) {
		this.status = status;
	}
	
	
	
}
