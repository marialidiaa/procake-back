package com.procake.v1.dtos;

import java.io.Serializable;

import com.procake.v1.dtos.validations.SenhaValidation;

import jakarta.validation.constraints.NotBlank;

public class RedefinirSenhaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Senha não pode ser vazio")
	@SenhaValidation
	private String senha;

	public RedefinirSenhaDTO() {

	}

	public RedefinirSenhaDTO(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
