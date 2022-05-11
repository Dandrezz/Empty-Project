/**
 * 
 */
package com.montran.exam.account;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 
 * @author Diego Portero
 *
 */
public abstract class BaseAccount {

	/**
	 * Account identifier
	 */
	private String idAccount;
	/**
	 * Name to be assigned to the account
	 */
	private String nameAccount;
	/**
	 * Account owner
	 */
	private String ownerAccount;
	/**
	 * Initial balance with which the account is created
	 */
	private BigDecimal initialBalance;
	/**
	 * Current balance
	 */
	private BigDecimal currentBalance;
	
	//	getters and setters
	public String getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}
	public String getNameAccount() {
		return nameAccount;
	}
	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}
	public String getOwnerAccount() {
		return ownerAccount;
	}
	public void setOwnerAccount(String ownerAccount) {
		this.ownerAccount = ownerAccount;
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
		return "BaseAccount [idAccount=" + idAccount + ", nameAccount=" + nameAccount + ", ownerAccount=" + ownerAccount
				+ ", initialBalance=" + initialBalance + ", currentBalance=" + currentBalance + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(currentBalance, idAccount, initialBalance, nameAccount, ownerAccount);
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
		return Objects.equals(currentBalance, other.currentBalance) && Objects.equals(idAccount, other.idAccount)
				&& Objects.equals(initialBalance, other.initialBalance)
				&& Objects.equals(nameAccount, other.nameAccount) && Objects.equals(ownerAccount, other.ownerAccount);
	}

}
