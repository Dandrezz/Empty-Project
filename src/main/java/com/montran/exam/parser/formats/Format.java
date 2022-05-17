package com.montran.exam.parser.formats;

/**
 * This class contains the base of the format information
 * 
 * @author Diego Portero
 *
 */
public abstract class Format {

	/**
	 * Sender participant identifier
	 */
	private String senderCode;

	/**
	 * Type currency
	 */
	private String currency;

	// getters and setters

	public String getCurrency() {
		return currency;
	}

	public String getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
