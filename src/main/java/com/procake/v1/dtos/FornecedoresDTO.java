package com.procake.v1.dtos;
import java.io.Serializable;
import java.util.UUID;

import com.procake.v1.dtos.validations.CpfCnpjValidation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FornecedoresDTO implements Serializable {
	
private static final long serialVersionUID = 1L; 
	
	private UUID id;
	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;
	@NotBlank(message = "O campo não pode ser vazio")
	@CpfCnpjValidation(message = "Campo inválido")
	private String cpfCnpj;
	@NotBlank(message = "O campo não pode ser vazio")
	private String telefone;
	@NotBlank(message = "E-mail não pode ser vazio")
	private String email;
	@NotNull(message = "O campo não pode ser vazio")
	private Boolean enabled;
	private String rua;
	@NotBlank(message = "O campo não pode ser vazio")
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
	
	public FornecedoresDTO() {}

	

	public FornecedoresDTO(UUID id, String nome, String cpfCnpj, String telefone, String email, Boolean enabled, String rua,String numero,String bairro,String cep,String cidade,String estado, String complemento) {
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.email = email;
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

	public String getNome() {
		return nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
