package com.procake.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procake.v1.models.EstoqueModel;

public interface EstoqueRespositories extends JpaRepository<EstoqueModel, UUID> {

	@Query("SELECT E FROM EstoqueModel E WHERE E.quantidade > 0 AND E.tipo = 0")
	Page<EstoqueModel> findAllEnabled(Pageable page);

	@Query("SELECT E FROM EstoqueModel E WHERE E.insumo.nome LIKE %:nome% AND E.quantidade > 0 AND E.tipo = 0")
	List<EstoqueModel> buscarPorNomeAtivo(String nome);

	@Query("SELECT E FROM EstoqueModel E WHERE E.insumo.id = :insumo AND E.quantidade > 0 AND E.tipo = 0")
	List<EstoqueModel> buscarPorInsumoAtivo(UUID insumo); 

	// Pesquisa completa insercao de estoque
	@Query("SELECT E FROM EstoqueModel E WHERE E.usuarioInsercao.nome LIKE %:nome% AND E.tipo = 0 AND E.tipo = 0")
	List<EstoqueModel> buscarLancamentoEstoquePorNomeUsuario(String nome);

	@Query("SELECT E FROM EstoqueModel E WHERE E.insumo.nome LIKE %:insumo% AND E.tipo = 0")
	List<EstoqueModel> buscarLancamentoEstoquePorInsumo(String insumo);

	@Query("SELECT E FROM EstoqueModel E WHERE E.dataInsercao BETWEEN :dataInicial AND :dataFinal AND E.tipo = 0")
	List<EstoqueModel> buscarLancamentoEstoquePorData(LocalDate dataInicial, LocalDate dataFinal);

	@Query("SELECT E FROM EstoqueModel E WHERE E.dataInsercao BETWEEN :dataInicial AND :dataFinal AND E.insumo.nome LIKE %:insumo% AND E.usuarioInsercao.nome LIKE %:usuario% AND E.tipo = 0")
	List<EstoqueModel> buscarLancamentoEstoque(String insumo, String usuario, LocalDate dataInicial, LocalDate dataFinal);
}
