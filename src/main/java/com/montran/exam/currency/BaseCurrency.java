package com.montran.exam.currency;

/**
 * This class is an abstract class that contains the common values that an currency will have in the system.
 * @author Diego Portero
 *
 */
public abstract class BaseCurrency {
	
	/**
	 * Code to be assigned to the account
	 */
	private String code;
	/**
	 * Number of digits that the decimal part of the currency will have
	 */
	private int fractionDigits;
	
	// getters and setters
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getFractionDigits() {
		return fractionDigits;
	}
	public void setFractionDigits(int fractionDigits) {
		this.fractionDigits = fractionDigits;
	}
	
}
