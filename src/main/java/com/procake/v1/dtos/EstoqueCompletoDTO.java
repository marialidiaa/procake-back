package com.procake.v1.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.procake.v1.models.InsumoModel;
import com.procake.v1.models.enums.Tipo;

public class EstoqueCompletoDTO {

	private UUID id;
	private Double quantidade;
	private LocalDate dataInsercao;
	private LocalDate dataAlteracao;
	private LocalDate dataVencimento;
	private Double valor;
	private String marca;
	private String notaFiscal;
	private Tipo tipo;

	private String usuarioInsercao;

	private String usuarioAlteracao;

	private InsumoModel insumo;

	public EstoqueCompletoDTO() {

	}

	public EstoqueCompletoDTO(UUID id, Double quantidade, LocalDate dataInsercao, LocalDate dataAlteracao,
			LocalDate dataVencimento, Double valor, String marca, String notaFiscal, String usuarioInsercao,
			String usuarioAlteracao, InsumoModel insumo, Tipo tipo) {
		this.id = id;
		this.quantidade = quantidade;
		this.dataInsercao = dataInsercao;
		this.dataAlteracao = dataAlteracao;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.marca = marca;
		this.notaFiscal = notaFiscal;
		this.usuarioInsercao = usuarioInsercao;
		this.usuarioAlteracao = usuarioAlteracao;
		this.insumo = insumo;
		this.tipo = tipo;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public LocalDate getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDate dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
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

	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public InsumoModel getInsumo() {
		return insumo;
	}

	public void setInsumo(InsumoModel insumo) {
		this.insumo = insumo;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
