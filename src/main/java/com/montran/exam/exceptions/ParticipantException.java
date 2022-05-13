package com.montran.exam.exceptions;

public class ParticipantException extends Exception {

	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = 8423430738799842844L;
	
	/**
	 * Empty constructor
	 */
	public ParticipantException() {
		super();
	}
	/**
	 * Constructor with message
	 * @param message
	 */
	public ParticipantException(String message) {
		super(message);
	}
	/**
	 * Constructor with message and cause
	 * @param message
	 * @param cause
	 */
	public ParticipantException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
