/**
 * 
 */
package com.montran.exam.account;

import javax.xml.bind.annotation.XmlRootElement;

import com.montran.exam.persistence.Archivable;
import com.montran.exam.status.Status;

/**
 * @author Diego Portero
 *
 */

@XmlRootElement(name = "data")
public class Account extends BaseAccount implements Archivable {

	private static final long serialVersionUID = 7637347516152579344L;
	
	/**
	 * Number of debits made by this account
	 */
	private int numberDebits;
	/**
	 * Number of credits that this account has made
	 */
	private int numberCredits;
	/**
	 * Current account status
	 */
	private Status status;
	// getters and setters
	public int getNumberDebits() {
		return numberDebits;
	}
	public void setNumberDebits(int numberDebits) {
		this.numberDebits = numberDebits;
	}
	public int getNumberCredits() {
		return numberCredits;
	}
	public void setNumberCredits(int numberCredits) {
		this.numberCredits = numberCredits;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Account [numberDebits=" + numberDebits + ", numberCredits=" + numberCredits + ", status=" + status
				+ ", getIdAccount()=" + getIdAccount() + ", getNameAccount()=" + getNameAccount()
				+ ", getOwnerAccount()=" + getOwnerAccount() + ", getInitialBalance()=" + getInitialBalance()
				+ ", getCurrentBalance()=" + getCurrentBalance() + ", toString()=" + super.toString() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + "]";
	}
	
	
	
}
