package com.procake.v1.dtos;

import java.io.Serializable;
import java.time.LocalDate;

public class EstoqueCompletoPesquisaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private String usuarioInsercao;
	private String insumo;

	public EstoqueCompletoPesquisaDTO() {

	}

	public EstoqueCompletoPesquisaDTO(LocalDate dataInicial, LocalDate dataFinal, String usuarioInsercao,
			String insumo) {
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.usuarioInsercao = usuarioInsercao;
		this.insumo = insumo;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getUsuarioInsercao() {
		return usuarioInsercao;
	}

	public void setUsuarioInsercao(String usuarioInsercao) {
		this.usuarioInsercao = usuarioInsercao;
	}

	public String getInsumo() {
		return insumo;
	}

	public void setInsumo(String insumo) {
		this.insumo = insumo;
	}
	
}