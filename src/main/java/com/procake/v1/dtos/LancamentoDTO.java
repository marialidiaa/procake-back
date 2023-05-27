package com.procake.v1.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import com.procake.v1.models.FornecedoresModel;
import com.procake.v1.models.InsumoModel;
import com.procake.v1.models.MarcaModel;
import com.procake.v1.models.NotaFiscalModel;
import com.procake.v1.models.UsuarioModel;
import com.procake.v1.models.enums.Tipo;

public class LancamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private Tipo tipo;
	private LocalDate dataCompra;
	private LocalDate dataInsercao;
	private LocalDate dataVencimento;
	private Double quantidade;
	private Double valorPago;
    private NotaFiscalModel notaFiscal;
	private UsuarioModel usuarioInsercao;
	private UsuarioModel usuarioAlteracao;
	private InsumoModel insumo;
	private MarcaModel marca;
	private FornecedoresModel fornecedor;
	
	public LancamentoDTO() {}

	public LancamentoDTO(UUID id, Tipo tipo, LocalDate dataCompra, LocalDate dataInsercao, LocalDate dataVencimento,
			Double quantidade, Double valorPago, NotaFiscalModel notaFiscal, UsuarioModel usuarioInsercao,
			UsuarioModel usuarioAlteracao, InsumoModel insumo, MarcaModel marca, FornecedoresModel fornecedor) {
		this.id = id;
		this.tipo = tipo;
		this.dataCompra = dataCompra;
		this.dataInsercao = dataInsercao;
		this.dataVencimento = dataVencimento;
		this.quantidade = quantidade;
		this.valorPago = valorPago;
		this.notaFiscal = notaFiscal;
		this.usuarioInsercao = usuarioInsercao;
		this.usuarioAlteracao = usuarioAlteracao;
		this.insumo = insumo;
		this.marca = marca;
		this.fornecedor = fornecedor;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
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

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public NotaFiscalModel getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscalModel notaFiscal) {
		this.notaFiscal = notaFiscal;
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

	public InsumoModel getInsumo() {
		return insumo;
	}

	public void setInsumo(InsumoModel insumo) {
		this.insumo = insumo;
	}

	public MarcaModel getMarca() {
		return marca;
	}

	public void setMarca(MarcaModel marca) {
		this.marca = marca;
	}

	public FornecedoresModel getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedoresModel fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	
}
