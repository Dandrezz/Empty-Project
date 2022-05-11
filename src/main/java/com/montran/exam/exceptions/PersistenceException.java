package com.montran.exam.exceptions;

/**
 * Class to manage the exception of persistence
 * 
 * @author Diego Portero
 *
 */
public class PersistenceException extends Throwable {

	/**
	 * Serializable ID
	 */
	public static final long serialVersionUID = -8588736130433456664L;

	/**
	 * Empty constructor
	 */
	public PersistenceException() {
		super();
	}

	/**
	 * Constructor with message
	 * 
	 * @param message
	 */
	public PersistenceException(String message) {
		super(message);
	}

	/**
	 * Constructor with cause
	 * 
	 * @param cause
	 */
	public PersistenceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor with message and cause
	 * 
	 * @param message
	 * @param cause
	 */
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}
