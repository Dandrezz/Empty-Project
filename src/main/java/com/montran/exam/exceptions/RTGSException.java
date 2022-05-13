package com.montran.exam.exceptions;

public class RTGSException extends Exception {

	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = 8423430738799142845L;
	
	/**
	 * Empty constructor
	 */
	public RTGSException() {
		super();
	}
	/**
	 * Constructor with message
	 * @param message
	 */
	public RTGSException(String message) {
		super(message);
	}
	/**
	 * Constructor with message and cause
	 * @param message
	 * @param cause
	 */
	public RTGSException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
