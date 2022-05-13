package com.montran.exam.exceptions;

public class TransactionException extends Exception {

	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = -2104652234068618008L;

	/**
	 * Empty constructor
	 */
	public TransactionException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 */
	public TransactionException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and cause
	 * 
	 * @param message
	 * @param cause
	 */
	public TransactionException(String message, Throwable cause) {
		super(message, cause);
	}

}
