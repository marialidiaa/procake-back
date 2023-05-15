package com.procake.v1.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_roles")
public class RoleModel implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@Column(name = "descricao", nullable = false)
	private String descricao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "tb_roles_grupos", 
	  joinColumns = @JoinColumn(name = "role_id"), 
	  inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private List<GrupoAcessoModel> grupos;

	public RoleModel() {
	}

	public RoleModel(UUID id, String nome, String descricao) {
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

	@Override
	public String getAuthority() {
		return this.nome;
	}
	@JsonIgnore
	public List<GrupoAcessoModel> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoAcessoModel> grupos) {
		this.grupos = grupos;
	}
}
