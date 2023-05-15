package com.procake.v1.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GrupoAcessoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;
	@NotNull(message = "Lista de roles não pode ser vazio")
	private List<RoleDTO> roles;

	public GrupoAcessoDTO() {
	}

	public GrupoAcessoDTO(UUID id, String nome) {
		this.id = id;
		this.nome = nome;
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

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	
}
