package com.procake.v1.dtos;

import java.time.LocalDate;

public class NotaFiscalPesquisaDTO {

	private String usuarioInsercao;
	private String notaFiscal;
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	
	public NotaFiscalPesquisaDTO() {
		
	}
	
	public NotaFiscalPesquisaDTO(String usuarioInsercao, String notaFiscal, LocalDate dataInicial,
			LocalDate dataFinal) {
		this.usuarioInsercao = usuarioInsercao;
		this.notaFiscal = notaFiscal;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	public String getUsuarioInsercao() {
		return usuarioInsercao;
	}

	public void setUsuarioInsercao(String usuarioInsercao) {
		this.usuarioInsercao = usuarioInsercao;
	}

	public String getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(String notaFiscal) {
		this.notaFiscal = notaFiscal;
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

}
