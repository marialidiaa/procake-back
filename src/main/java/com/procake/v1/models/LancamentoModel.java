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
@Table(name = "tb_lancamento")
public class LancamentoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(name = "tipo", nullable = false, unique = false)
	private Tipo tipo;
	@Column(name = "data_compra", nullable = false, unique = false)
	private LocalDate dataCompra;
	@Column(name = "data_insercao", nullable = false, unique = false)
	private LocalDate dataInsercao;
	@Column(name = "data_vencimento", nullable = false, unique = false)
	private LocalDate dataVencimento;
	@Column(name = "quantidade", nullable = false, unique = false)
	private Double quantidade;
	@Column(name = "valor_pago", nullable = false, unique = false)
	private Double valorPago;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="nota_fiscal_id", nullable=false)
    private NotaFiscalModel notaFiscal;

	@ManyToOne
	@JoinColumn(name = "usuario_insercao_id", nullable = false)
	private UsuarioModel usuarioInsercao;

	@ManyToOne
	@JoinColumn(name = "usuario_alteracao_id")
	private UsuarioModel usuarioAlteracao;


	@ManyToOne
	@JoinColumn(name = "insumo_id", nullable = false)
	private InsumoModel insumo;
	
	
	@ManyToOne
	@JoinColumn(name = "marca_id", nullable = false)
	private MarcaModel marca;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private FornecedoresModel fornecedor;
	
	public LancamentoModel() {
		
	}

	public LancamentoModel(UUID id, Tipo tipo, LocalDate dataCompra, LocalDate dataInsercao, LocalDate dataVencimento,
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
