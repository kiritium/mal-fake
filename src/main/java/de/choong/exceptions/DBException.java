package de.choong.exceptions;

/**
 * Database Exception.
 *
 */
public class DBException extends Exception {

	private static final long serialVersionUID = -4635478366376122809L;

	public DBException(String message) {
		super(message);
	}
	
	public DBException(String message, Throwable ex) {
		super(message, ex);
	}
	
}
