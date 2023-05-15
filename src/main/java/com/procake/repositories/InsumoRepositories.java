package com.procake.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procake.v1.models.InsumoModel;

public interface InsumoRepositories extends JpaRepository<InsumoModel, UUID>{
	
	@Query("SELECT U FROM InsumoModel U WHERE U.enabled = TRUE")
	Page<InsumoModel> listarTodosAtivos(Pageable pageable);
	
	@Query("SELECT U FROM InsumoModel U WHERE U.enabled = FALSE")
	Page<InsumoModel> listarTodosDesativados(Pageable pageable);
	
	@Query("SELECT U FROM InsumoModel U WHERE U.nome LIKE %:nome%")
	List<InsumoModel> buscarPorNome(String nome);
	
	@Query("SELECT U FROM InsumoModel U WHERE U.nome LIKE %:nome% AND U.enabled = TRUE")
	List<InsumoModel> buscarAtivoPorNome(String nome);
}
