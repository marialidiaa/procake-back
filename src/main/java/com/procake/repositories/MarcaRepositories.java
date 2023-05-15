package com.procake.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procake.v1.models.MarcaModel;

public interface MarcaRepositories extends JpaRepository<MarcaModel, UUID>{
	@Query("SELECT U FROM MarcaModel U WHERE U.enabled = TRUE")
	Page<MarcaModel> listarTodosAtivos(Pageable pageable);
	
	@Query("SELECT U FROM MarcaModel U WHERE U.enabled = FALSE")
	Page<MarcaModel> listarTodosDesativados(Pageable pageable);
	
	@Query("SELECT U FROM MarcaModel U WHERE U.nome LIKE %:nome%")
	List<MarcaModel> buscarPorNome(String nome);
	
	@Query("SELECT U FROM MarcaModel U WHERE U.nome LIKE %:nome% AND U.enabled = TRUE")
	List<MarcaModel> buscarAtivoPorNome(String nome);

}
