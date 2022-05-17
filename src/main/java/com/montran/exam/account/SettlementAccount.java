package com.montran.exam.account;

import com.montran.exam.persistence.Archivable;

/**
 * This class represents the information necessary to create a suspense account
 * 
 * @author Diego Portero
 *
 */
public class SettlementAccount extends BaseAccount implements Archivable {

	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = -4710699361359320382L;
	/**
	 * Number of debits made by this account
	 */
	private int numberOfDebits;
	/**
	 * Number of credits that this account has made
	 */
	private int numberOfCredits;

	// getters and setters
	public int getNumberOfDebits() {
		return numberOfDebits;
	}

	public void setNumberOfDebits(int numberOfDebits) {
		this.numberOfDebits = numberOfDebits;
	}

	public int getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	@Override
	public String toString() {
		return "SettlementAccount [numberOfDebits=" + numberOfDebits + ", numberOfCredits=" + numberOfCredits
				+ ", getIdAccount()=" + getIdAccount() + ", getOwnerAcount()=" + getOwnerAcount() + ", getCurrency()="
				+ getCurrency() + ", getInitialBalance()=" + getInitialBalance() + ", getCurrentBalance()="
				+ getCurrentBalance() + ", toString()=" + super.toString() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + "]";
	}

}
