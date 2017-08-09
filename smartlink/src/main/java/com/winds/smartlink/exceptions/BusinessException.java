package com.winds.smartlink.exceptions;

/**
 * Class exception for business layer.
 * @author Windreams
 *
 */
public class BusinessException extends Exception{

	private static final long serialVersionUID = -2366884723308443958L;

	/**
	 * Instantiates a new business exception.
	 */
	public BusinessException() {
	}

	/**
	 * Instantiates a new business exception.
	 * 
	 * @param message
	 *            the message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new business exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public BusinessException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new business exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
