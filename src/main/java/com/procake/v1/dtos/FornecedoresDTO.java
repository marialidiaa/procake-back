package com.procake.v1.dtos;
import java.io.Serializable;
import java.util.UUID;

import com.procake.v1.dtos.validations.CpfCnpjValidation;

import jakarta.validation.constraints.NotBlank;

public class FornecedoresDTO implements Serializable {
	
private static final long serialVersionUID = 1L; 
	
	private UUID id;
	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;
	@NotBlank(message = "O campo não pode ser vazio")
	@CpfCnpjValidation(message = "Campo inválido")
	private String cpfCnpj;
	@NotBlank(message = "E-mail não pode ser vazio")
	private String email;
	private boolean enabled;
	
	public FornecedoresDTO() {}

	public FornecedoresDTO(UUID id, String nome, String email, String cpfCnpj, boolean enabled) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.enabled = enabled;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
