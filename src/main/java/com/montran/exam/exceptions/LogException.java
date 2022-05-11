package com.montran.exam.exceptions;

/**
 * Class to manage the exception of generate a log
 * 
 * @author Diego Portero
 *
 */
public class LogException extends Exception {

	/**
	 * Serializable ID
	 */
	public static final long serialVersionUID = -70196400031204099L;

	/**
	 * Empty constructor
	 */
	public LogException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 */
	public LogException(String message) {
		super(message);
	}

	/**
	 * Constructor with cause
	 * 
	 * @param cause
	 */
	public LogException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with message and cause
	 * 
	 * @param message
	 * @param cause
	 */
	public LogException(String message, Throwable cause) {
		super(message, cause);
	}
}
