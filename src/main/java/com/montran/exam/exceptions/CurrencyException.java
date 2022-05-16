package com.montran.exam.exceptions;

/**
 * Class to manage the exception of currency
 * 
 * @author Diego Portero
 *
 */
public class CurrencyException extends Exception {

	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = 2356819790226182845L;

	/**
	 * Empty constructor
	 */
	public CurrencyException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 */
	public CurrencyException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and cause
	 * 
	 * @param message
	 * @param cause
	 */
	public CurrencyException(String message, Throwable cause) {
		super(message, cause);
	}

}
