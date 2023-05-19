package com.procake.exceptions;

public class EmailException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EmailException(String string) {
		super(string);
	}

}
