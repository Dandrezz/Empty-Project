package com.montran.exam.exceptions;

/**
 * @author Diego Portero
 *
 */
public class AchException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 8423430738799842846L;

	/**
	 * Empty constructor
	 */
	public AchException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 */
	public AchException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and cause
	 * 
	 * @param message
	 * @param cause
	 */
	public AchException(String message, Throwable cause) {
		super(message, cause);
	}

}
