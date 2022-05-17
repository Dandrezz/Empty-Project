package com.montran.exam.exceptions;

public class NotificationException extends Exception {

	/**
	 * Serial version id
	 */

	private static final long serialVersionUID = 3230818643260432753L;

	/**
	 * Empty constructor
	 */
	public NotificationException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 */
	public NotificationException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and cause
	 * 
	 * @param message
	 * @param cause
	 */
	public NotificationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
