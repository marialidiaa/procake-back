package com.procake.utils;

import com.procake.v1.models.ClienteModel;
import com.procake.v1.models.LancamentoModel;
import com.procake.v1.models.FornecedoresModel;
import com.procake.v1.models.InsumoModel;
import com.procake.v1.models.MarcaModel;
import com.procake.v1.models.NotaFiscalModel;
import com.procake.v1.models.UsuarioModel;

public class ToUpper {
	
	public static void UPPER_USUARIO_MODEL(UsuarioModel model) {
		model.setNome(model.getNome().trim().toUpperCase());
		model.setUsername(model.getUsername().trim().toUpperCase());
	}

	public static void UPPER_INSUMO_MODEL(InsumoModel model) {
		model.setNome(model.getNome().trim().toUpperCase());
		model.setDescricao(model.getDescricao().trim().toUpperCase());
	}
	
	public static void UPPER_NOTA_FISCAL_MODEL(NotaFiscalModel model) {
//		model.setMarca(model.getMarca().trim().toUpperCase());
//		model.setNotaFiscal(model.getNotaFiscal().trim().toUpperCase());
	}
	
	public static void UPPER_LANCAMENTO_MODEL(LancamentoModel model) {
//		model.setMarca(model.getMarca().trim().toUpperCase());
//		model.setNotaFiscal(model.getNotaFiscal().trim().toUpperCase());
	}
	
	public static void UPPER_MARCA_MODEL(MarcaModel model) {
		model.setNome(model.getNome().trim().toUpperCase());
	}

	public static void UPPER_FORNECEDORES_MODEL(FornecedoresModel model) {
		model.setNome(model.getNome().trim().toUpperCase());
		model.setEmail(model.getEmail().trim().toUpperCase());
		model.setBairro(model.getBairro().trim().toUpperCase());
		model.setCidade(model.getCidade().trim().toUpperCase());
		if (model.getComplemento()!= null) {
			model.setComplemento(model.getComplemento().trim().toUpperCase());
		}
		if (model.getRua()!= null) {
			model.setRua(model.getRua().trim().toUpperCase());
		}
		model.setEstado(model.getEstado().trim().toUpperCase());
		model.setNumero(model.getNumero().trim().toUpperCase());
	}
	
	public static void UPPER_CLIENTE_MODEL(ClienteModel model) {
		model.setNome(model.getNome().trim().toUpperCase());
		model.setEmail(model.getEmail().trim().toUpperCase());
		model.setBairro(model.getBairro().trim().toUpperCase());
		model.setCidade(model.getCidade().trim().toUpperCase());
		model.setEstado(model.getEstado().trim().toUpperCase());
		if (model.getRua() != null ) {
			model.setRua(model.getRua().trim().toUpperCase());}
		if (model.getComplemento()!= null) {
			model.setComplemento(model.getComplemento().trim().toUpperCase());}
		model.setNumero(model.getNumero().trim().toUpperCase());
	}
}
