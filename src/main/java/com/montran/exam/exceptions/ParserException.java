package com.montran.exam.exceptions;

public class ParserException extends Exception{

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 8423430738799842846L;

	/**
	 * Empty constructor
	 */
	public ParserException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 */
	public ParserException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and cause
	 * 
	 * @param message
	 * @param cause
	 */
	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
