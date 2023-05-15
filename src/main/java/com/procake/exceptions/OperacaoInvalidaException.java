package com.procake.exceptions;

public class OperacaoInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public OperacaoInvalidaException(String string) {
		super(string);
	}
}
