package com.procake.v1.models;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_fornecedores")
public class FornecedoresModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	@Column(nullable = false, name = "nome")
	private String nome;
	@Column(nullable = false, unique = true, name = "email")
	private String email;
	@Column(nullable = false, unique = true, name = "cpf_cnpj")
	private String cpfCnpj;
	@Column(nullable = false, unique = true, name = "telefone")
	private String telefone;
	@Column(nullable = true, name = "rua")
	private String rua;
	@Column(nullable = false, name = "numero")
	private String numero;
	@Column(nullable = false, name = "bairro")
	private String bairro;
	@Column(nullable = false, name = "cep")
	private String cep;
	@Column(nullable = false, name = "cidade")
	private String cidade;
	@Column(nullable = false, name = "estado")
	private String estado;
	@Column(nullable = false, name = "complemento")
	private String complemento;
	@Column(nullable = false, name = "enabled")
	private Boolean enabled;
	
	public FornecedoresModel() {}
	
	public FornecedoresModel(UUID id, String nome, String email, String cpfCnpj, String telefone, String rua, String numero, String bairro, String cep, String cidade,
			String estado, String complemento, Boolean enabled) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
		this.enabled = enabled;
	}

	public UUID getId() {
		return id;
	}

	public String getTelefone() {
		return telefone;
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

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
