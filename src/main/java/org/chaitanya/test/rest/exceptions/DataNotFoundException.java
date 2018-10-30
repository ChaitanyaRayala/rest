package org.chaitanya.test.rest.exceptions;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6999567679823406454L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
