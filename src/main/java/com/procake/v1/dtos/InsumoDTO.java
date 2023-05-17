package com.procake.v1.dtos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

import com.procake.v1.models.enums.UnidadeMedida;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InsumoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private BigInteger codigo; 
	@NotBlank(message = "Nome não pode ser vazio")
	private String nome;
	@NotNull(message = "Unidade de medida não pode ser vazio")
	private UnidadeMedida unidadeMedida;
	@NotBlank(message = "Descrição não pode ser vazio")
	private String descricao;
	private boolean enabled;
	private Double quantidade;

	public InsumoDTO() {

	}

	public InsumoDTO(UUID id, BigInteger codigo, String nome, UnidadeMedida unidadeMedida, String descricao,
			boolean enabled, Double quantidade) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.unidadeMedida = unidadeMedida;
		this.descricao = descricao;
		this.enabled = enabled;
		this.quantidade = quantidade;
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

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public BigInteger getCodigo() {
		return codigo;
	}

	public void setCodigo(BigInteger codigo) {
		this.codigo = codigo;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double qtd) {
		this.quantidade = qtd;
	}
	
}
