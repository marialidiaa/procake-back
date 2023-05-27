package com.procake.v1.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.procake.v1.models.enums.StatusNotaFiscal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_nota_fiscal")
public class NotaFiscalModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Column(name = "nota_fiscal", nullable = false, unique = false)
	private String notaFiscal;
	@ManyToOne
	@JoinColumn(name = "usuario_insercao_id", nullable = false)
	private UsuarioModel usuarioInsercao;
	@Column(name = "data_insercao", nullable = false, unique = false)
	private LocalDate dataInsercao;
	@Column(name = "data_compra", nullable = false, unique = false)
	private LocalDate dataCompra;
	@Column(name = "status", nullable = false, unique = false)
	private StatusNotaFiscal status;
	@OneToMany(mappedBy = "notaFiscal")
	private List<LancamentoModel> lancamentos = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", nullable = false)
	private FornecedoresModel fornecedor;

	public NotaFiscalModel() {

	}

	public NotaFiscalModel(UUID id, String notaFiscal, UsuarioModel usuarioInsercao, LocalDate dataInsercao,
			LocalDate dataCompra, StatusNotaFiscal status, FornecedoresModel fornecedor) {
		this.id = id;
		this.notaFiscal = notaFiscal;
		this.usuarioInsercao = usuarioInsercao;
		this.dataInsercao = dataInsercao;
		this.dataCompra = dataCompra;
		this.status = status;
		this.fornecedor = fornecedor;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public UsuarioModel getUsuarioInsercao() {
		return usuarioInsercao;
	}

	public void setUsuarioInsercao(UsuarioModel usuarioInsercao) {
		this.usuarioInsercao = usuarioInsercao;
	}

	public LocalDate getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(LocalDate dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public StatusNotaFiscal getStatus() {
		return status;
	}

	public void setStatus(StatusNotaFiscal status) {
		this.status = status;
	}

	public List<LancamentoModel> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoModel> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public FornecedoresModel getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedoresModel fornecedor) {
		this.fornecedor = fornecedor;
	}

	
	
	
}
