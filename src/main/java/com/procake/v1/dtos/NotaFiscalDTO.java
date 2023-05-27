package com.procake.v1.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.procake.v1.models.LancamentoModel;
import com.procake.v1.models.enums.StatusNotaFiscal;

public class NotaFiscalDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private String notaFiscal;
	private UsuarioDTO usuarioInsercao;
	private LocalDate dataInsercao;
	private LocalDate dataCompra;
	private StatusNotaFiscal status;
	private List<LancamentoModel> lancamentos = new ArrayList<>();
	private FornecedoresDTO fornecedor;

	public NotaFiscalDTO() {

	}

	public NotaFiscalDTO(UUID id, String notaFiscal, UsuarioDTO usuarioInsercao, LocalDate dataInsercao,
			LocalDate dataCompra, StatusNotaFiscal status, FornecedoresDTO fornecedor) {
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

	public UsuarioDTO getUsuarioInsercao() {
		return usuarioInsercao;
	}

	public void setUsuarioInsercao(UsuarioDTO usuarioInsercao) {
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

	public FornecedoresDTO getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedoresDTO fornecedor) {
		this.fornecedor = fornecedor;
	}

}
