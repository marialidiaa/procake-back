package com.procake.v1.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.procake.v1.models.enums.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_estoque")
public class EstoqueModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(name = "quantidade", nullable = false)
	private Double quantidade;
	@Column(name = "data_insercao", nullable = false)
	private LocalDate dataInsercao;
	@Column(name = "data_alteracao")
	private LocalDate dataAlteracao;
	@Column(name = "data_vencimento", nullable = false)
	private LocalDate dataVencimento;
	@Column(name = "valor", nullable = false)
	private Double valor;
	@Column(name = "marca")
	private String marca;
	@Column(name = "nota_fiscal")
	private String notaFiscal;
	@Column(name = "tipo", nullable = false, unique = false)
	private Tipo tipo;

	@ManyToOne
	@JoinColumn(name = "usuario_insercao_id", nullable = false)
	private UsuarioModel usuarioInsercao;

	@ManyToOne
	@JoinColumn(name = "usuario_alteracao_id")
	private UsuarioModel usuarioAlteracao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "insumo_id", nullable = false)
	private InsumoModel insumo;

	public EstoqueModel() {

	}

	public EstoqueModel(UUID id, Double quantidade, LocalDate dataInsercao, LocalDate dataAlteracao,
			LocalDate dataVencimento, Double valor, String marca, String notaFiscal, Tipo tipo,
			UsuarioModel usuarioInsercao, UsuarioModel usuarioAlteracao, InsumoModel insumo) {
		this.id = id;
		this.quantidade = quantidade;
		this.dataInsercao = dataInsercao;
		this.dataAlteracao = dataAlteracao;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.marca = marca;
		this.notaFiscal = notaFiscal;
		this.tipo = tipo;
		this.usuarioInsercao = usuarioInsercao;
		this.usuarioAlteracao = usuarioAlteracao;
		this.insumo = insumo;
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

	public InsumoModel getInsumo() {
		return insumo;
	}

	public void setInsumo(InsumoModel insumo) {
		this.insumo = insumo;
	}

	public LocalDate getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDate dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public UsuarioModel getUsuarioInsercao() {
		return usuarioInsercao;
	}

	public void setUsuarioInsercao(UsuarioModel usuarioInsercao) {
		this.usuarioInsercao = usuarioInsercao;
	}

	public UsuarioModel getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(UsuarioModel usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
