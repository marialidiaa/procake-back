package com.procake.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontradoException(String string) {
		super(string);
	}
}