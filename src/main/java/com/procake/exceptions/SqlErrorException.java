package com.procake.exceptions;

public class SqlErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public SqlErrorException(String string) {
		super(string);
	}
}
