package com.montran.exam.transaction.impl;

import com.montran.exam.transaction.BaseTransaction;

/**
 * This class contains the necessary values to create a new transaction of type ACH
 * @author Diego Portero
 *
 */
public class TransacionAch extends BaseTransaction {

	/**
	 * Number of individual payments contained in the transaction
	 */
	private int numberIndivitualPayments;

	// getters and setters
	
	public int getNumberIndivitualPayments() {
		return numberIndivitualPayments;
	}

	public void setNumberIndivitualPayments(int numberIndivitualPayments) {
		this.numberIndivitualPayments = numberIndivitualPayments;
	}
	
}
