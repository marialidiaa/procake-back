 package com.procake.v1.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
@Table(name = "tb_grupo_acesso")
public class GrupoAcessoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "tb_roles_grupos", 
	  joinColumns = @JoinColumn(name = "grupo_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleModel> roles;

	public GrupoAcessoModel() {
	}

	public GrupoAcessoModel(UUID id, String nome) {
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

	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	
	public void addRole(RoleModel role) {
		roles.add(role);
	}
}
