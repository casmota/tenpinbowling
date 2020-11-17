package com.casmota.tenpinbowling.exception;

/**
 * Invalid parameters exception.
 */
public class InvalidParameterException extends RuntimeException {

	/**
	 * Constructor.
	 *
	 * @param message detail message
	 */
	public InvalidParameterException(String message) {
		super(message);
	}
}
