	package com.procake.v1.dtos;

import java.io.Serializable;
import java.util.UUID;

import com.procake.v1.dtos.validations.CpfCnpjValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	@NotBlank(message = "O campo não pode ser vazio")
	private String nome;
	@NotBlank(message = "O campo não pode ser vazio")
	@Email(message = "Campo inválido")
	private String email;
	@NotBlank(message = "O campo não pode ser vazio")
	private String telefone;
	@NotBlank(message = "O campo não pode ser vazio")
	@CpfCnpjValidation(message = "Campo inválido")
	private String cpfCnpj;
	@NotNull(message = "O campo não pode ser vazio")
	private Boolean enabled;
	@NotBlank(message = "O campo não pode ser vazio")
	private String rua;
	private String numero;
	@NotBlank(message = "O campo não pode ser vazio")
	private String bairro;
	@NotBlank(message = "O campo não pode ser vazio")
	private String cep;
	@NotBlank(message = "O campo não pode ser vazio")
	private String cidade;
	@NotBlank(message = "O campo não pode ser vazio")
	private String estado;
	private String complemento;
	
	public ClienteDTO() {}
	
	public ClienteDTO(UUID id, String nome, String email, String telefone, String cpfCnpj, Boolean enabled, String rua,
			String numero, String bairro, String cep, String cidade, String estado, String complemento) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cpfCnpj = cpfCnpj;
		this.enabled = enabled;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
		
}
