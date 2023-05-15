package com.procake.v1.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class EstoqueDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private Integer codigo;
	@NotNull(message = "Quantidade não pode ser vazio")
	private Double quantidade;
	private LocalDate dataInsercao;
	@NotNull(message = "Data Vencimento não pode ser vazio")
	private LocalDate dataVencimento;
	@NotNull(message = "Valor não pode ser vazio")
	private Double valor;
	@NotNull(message = "Marca não pode ser vazio")
	private String marca;
	private String notaFiscal;

	private String usuarioInsercao;

	private InsumoDTO insumo;

	public EstoqueDTO() {

	}

	public EstoqueDTO(UUID id, Integer codigo, Double quantidade, LocalDate dataInsercao, LocalDate dataVencimento,
			Double valor, String marca, String notaFiscal, String usuarioInsercao, InsumoDTO insumo) {
		this.id = id;
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.dataInsercao = dataInsercao;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.marca = marca;
		this.notaFiscal = notaFiscal;
		this.usuarioInsercao = usuarioInsercao;
		this.insumo = insumo;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(LocalDate dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getUsuarioInsercao() {
		return usuarioInsercao;
	}

	public void setUsuarioInsercao(String usuarioInsercao) {
		this.usuarioInsercao = usuarioInsercao;
	}

	public InsumoDTO getInsumo() {
		return insumo;
	}

	public void setInsumo(InsumoDTO insumo) {
		this.insumo = insumo;
	}

}