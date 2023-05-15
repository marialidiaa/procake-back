package com.procake.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procake.v1.models.ClienteModel;


public interface ClienteRepositories extends JpaRepository<ClienteModel, UUID>{
	@Query("SELECT U FROM ClienteModel U WHERE U.enabled = TRUE")
	Page<ClienteModel> listarTodosAtivos(Pageable pageable);
	
	@Query("SELECT U FROM ClienteModel U WHERE U.enabled = FALSE")
	Page<ClienteModel> listarTodosDesativados(Pageable pageable);
	
	@Query("SELECT U FROM ClienteModel U WHERE U.nome LIKE %:nome%")
	List<ClienteModel> buscarPorNome(String nome);
	
	@Query("SELECT U FROM ClienteModel U WHERE U.nome LIKE %:nome% AND U.enabled = TRUE")
	List<ClienteModel> buscarAtivoPorNome(String nome);

}
