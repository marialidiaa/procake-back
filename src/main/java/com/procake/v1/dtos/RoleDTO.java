package com.procake.v1.dtos;

import java.io.Serializable;
import java.util.UUID;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private String nome;
	private String descricao;

	public RoleDTO() {
	}

	public RoleDTO(UUID id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
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


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
