package com.montran.exam.transaction;

/**
 * This class contains the necessary values to create a new transaction of type ACH
 * @author Diego Portero
 *
 */
public class TransacionAch extends BasicTransaction {

	/**
	 * Number of individual payments contained in the transaction
	 */
	private int numberIndivitualPayments;

	public int getNumberIndivitualPayments() {
		return numberIndivitualPayments;
	}

	public void setNumberIndivitualPayments(int numberIndivitualPayments) {
		this.numberIndivitualPayments = numberIndivitualPayments;
	}
	
}
