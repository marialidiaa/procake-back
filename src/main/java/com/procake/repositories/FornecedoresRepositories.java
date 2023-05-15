package com.procake.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procake.v1.models.FornecedoresModel;

public interface FornecedoresRepositories extends JpaRepository<FornecedoresModel, UUID> {
	@Query("SELECT U FROM FornecedoresModel U WHERE U.enabled = TRUE")
	Page<FornecedoresModel> listarTodosAtivos(Pageable pageable);
	
	@Query("SELECT U FROM FornecedoresModel U WHERE U.enabled = FALSE")
	Page<FornecedoresModel> listarTodosDesativados(Pageable pageable);
	
	@Query("SELECT U FROM FornecedoresModel U WHERE U.nome LIKE %:nome%")
	List<FornecedoresModel> buscarPorNome(String nome);
	
	@Query("SELECT U FROM FornecedoresModel U WHERE U.nome LIKE %:nome% AND U.enabled = TRUE")
	List<FornecedoresModel> buscarAtivoPorNome(String nome);
}
