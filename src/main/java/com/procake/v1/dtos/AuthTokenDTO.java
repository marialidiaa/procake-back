package com.procake.v1.dtos;

import java.io.Serializable;

public class AuthTokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tokenAcesso;

	public AuthTokenDTO() {

	}

	public AuthTokenDTO(String tokenAcesso) {
		this.tokenAcesso = tokenAcesso;
	}

	public String getTokenAcesso() {
		return tokenAcesso;
	}

	public void setTokenAcesso(String tokenAcesso) {
		this.tokenAcesso = tokenAcesso;
	}

}