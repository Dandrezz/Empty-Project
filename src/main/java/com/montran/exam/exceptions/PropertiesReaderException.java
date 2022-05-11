package com.montran.exam.exceptions;

/**
 * Class to manage the exception when a properties file no exist
 * 
 * @author Diego Portero
 *
 */
public class PropertiesReaderException extends Throwable {
	/**
	 * Serializable ID
	 */
	public static final long serialVersionUID = 4750462261369124445L;

	/**
	 * Constructor with only a message
	 * 
	 * @param message
	 */
	public PropertiesReaderException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and a cause
	 * 
	 * @param message
	 * @param cause
	 */
	public PropertiesReaderException(String message, Throwable cause) {
		super(message, cause);
	}
}
