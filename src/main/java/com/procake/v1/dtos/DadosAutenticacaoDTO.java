package com.procake.v1.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class DadosAutenticacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Email não pode ser vazio")
	@Email(message = "O Email fornecido não é valido")
	private String email;
	@NotBlank(message = "Senha não pode ser vazio")
	private String senha;

	public DadosAutenticacaoDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
