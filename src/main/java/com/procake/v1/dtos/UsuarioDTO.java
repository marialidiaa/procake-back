package com.procake.v1.dtos;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.procake.v1.dtos.validations.CpfCnpjValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;
	@NotBlank(message = "Email não pode ser vazio")
	@Email(message = "Email não pode ser vazio")
	private String username;
	@NotNull(message = "Grupo de acesso não pode ser vazio")
	private GrupoAcessoDTO grupoAcesso;
	@NotBlank(message = "O campo não pode ser vazio")
	@CpfCnpjValidation(message = "Campo inválido")
	private String cpfCnpj;
	@NotBlank(message = "O campo não pode ser vazio")
	private String telefone;
	@NotNull(message = "Enabled não pode ser vazio")
	private Boolean enabled;

	public UsuarioDTO() {
	}

	public UsuarioDTO(String nome, String username, GrupoAcessoDTO grupoAcesso, String cpfCnpj, String telefone, Boolean enabled) {
		this.nome = nome;
		this.username = username;
		this.grupoAcesso = grupoAcesso;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.enabled = enabled;
	}


	public String getUsername() {
		return this.username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public GrupoAcessoDTO getGrupoAcesso() {
		return grupoAcesso;
	}

	public void setGrupoAcesso(GrupoAcessoDTO grupoAcesso) {
		this.grupoAcesso = grupoAcesso;
	}

	public void setUsername(String username) {
		this.username = username;
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
		UsuarioDTO other = (UsuarioDTO) obj;
		return Objects.equals(id, other.id);
	}

}
