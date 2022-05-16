package com.montran.exam.account;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * This class is an abstract class that contains the common values that an account  will have in the system
 * @author Diego Portero
 *
 */
public abstract class BaseAccount {

	/**
	 * Account identifier
	 */
	private int idAccount;
	/**
	 * Account owner
	 */
	private int ownerAcount;
	/**
	 * Type currency
	 */
	private String currency;
	/**
	 * Initial balance with which the account is created
	 */
	private BigDecimal initialBalance;
	/**
	 * Current balance
	 */
	private BigDecimal currentBalance;
	
	// getters and setters
	
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public int getOwnerAcount() {
		return ownerAcount;
	}
	public void setOwnerAcount(int ownerAcount) {
		this.ownerAcount = ownerAcount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}
	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	@Override
	public String toString() {
		return "BaseAccount [idAccount=" + idAccount + ", ownerAcount=" + ownerAcount + ", currency=" + currency
				+ ", initialBalance=" + initialBalance + ", currentBalance=" + currentBalance + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idAccount);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseAccount other = (BaseAccount) obj;
		return idAccount == other.idAccount;
	}
	
}
