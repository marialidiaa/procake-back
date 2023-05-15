package com.procake.v1.dtos;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public class MarcaDTO implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	private UUID id;
	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;
	private boolean enabled;
	
	public MarcaDTO() {}

	public MarcaDTO(UUID id, String nome, boolean enabled) {
		this.id = id;
		this.nome = nome;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
