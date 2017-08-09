package com.winds.smartlink.exceptions;

/**
 * Class exception for data access.
 * @author Windreams
 *
 */
public class DataAccessException extends Exception {
	
	private static final long serialVersionUID = 6623910525041139865L;
	
	/**
	 * Instantiates a new data access exception.
	 */
	public DataAccessException() {
	}

	/**
	 * Instantiates a new data access exception.
	 * 
	 * @param message
	 *            the message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new data access exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public DataAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new data access exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
