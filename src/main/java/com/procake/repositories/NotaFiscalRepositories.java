package com.procake.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procake.v1.models.NotaFiscalModel;

public interface NotaFiscalRepositories extends JpaRepository<NotaFiscalModel, UUID>{
	
	@Query("SELECT N FROM NotaFiscalModel N WHERE N.status = 0")
	Page<NotaFiscalModel> listarTodosLancados(Pageable pageable);
	
	@Query("SELECT N FROM NotaFiscalModel N WHERE N.status = 1")
	Page<NotaFiscalModel> listarTodosModificados(Pageable pageable);

	// Pesquisa completa lancamento
	@Query("SELECT N FROM NotaFiscalModel N WHERE N.usuarioInsercao.nome LIKE %:nome%")
	List<NotaFiscalModel> buscarNotaPorNomeUsuario(String nome);

	@Query("SELECT N FROM NotaFiscalModel N WHERE N.notaFiscal LIKE %:nota%")
	List<NotaFiscalModel> buscarNotaPorValor(String nota);

	@Query("SELECT N FROM NotaFiscalModel N WHERE N.dataCompra BETWEEN :dataInicial AND :dataFinal")
	List<NotaFiscalModel> buscarNotaPorData(LocalDate dataInicial, LocalDate dataFinal);

	@Query("SELECT N FROM NotaFiscalModel N WHERE N.dataCompra BETWEEN :dataInicial AND :dataFinal AND N.notaFiscal LIKE %:nota% AND N.usuarioInsercao.nome LIKE %:usuario%")
	List<NotaFiscalModel> buscarNota(String nota, String usuario, LocalDate dataInicial, LocalDate dataFinal);
}