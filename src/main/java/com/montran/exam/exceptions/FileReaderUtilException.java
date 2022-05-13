package com.montran.exam.exceptions;

public class FileReaderUtilException extends Exception {

	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = 8423430738799842845L;
	
	/**
	 * Empty constructor
	 */
	public FileReaderUtilException() {
		super();
	}
	/**
	 * Constructor with message
	 * @param message
	 */
	public FileReaderUtilException(String message) {
		super(message);
	}
	/**
	 * Constructor with message and cause
	 * @param message
	 * @param cause
	 */
	public FileReaderUtilException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
