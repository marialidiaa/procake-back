package com.procake.v1.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuarios")
public class UsuarioModel implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	@Column(nullable = false, name = "nome")
	private String nome;
	@Column(nullable = false, unique = true, name = "username")
	private String username;
	@Column(nullable = false, name = "password")
	private String password;
	@Column(nullable = false, unique = true, name = "cpf_cnpj")
	private String cpfCnpj;
	@Column(nullable = false, unique = true, name = "telefone")
	private String telefone;
	@Column(nullable = false, name = "enabled")
	private Boolean enabled;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "grupo_acesso_id")
	private GrupoAcessoModel grupoAcesso;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuarioInsercao", fetch = FetchType.EAGER)
	private List<EstoqueModel> estoque = new ArrayList<>();

	public UsuarioModel() {
	}

	public UsuarioModel(String nome, String username, String password, String cpfCnpj, String telefone, Boolean enabled,
			GrupoAcessoModel grupoAcesso) {
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.telefone = telefone;
		this.cpfCnpj = cpfCnpj;
		this.enabled = enabled;
		this.grupoAcesso = grupoAcesso;
	}

	
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grupoAcesso.getRoles();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
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

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public GrupoAcessoModel getGrupoAcesso() {
		return grupoAcesso;
	}

	public void setGrupoAcesso(GrupoAcessoModel accessGroup) {
		this.grupoAcesso = accessGroup;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioModel other = (UsuarioModel) obj;
		return Objects.equals(id, other.id);
	}

}