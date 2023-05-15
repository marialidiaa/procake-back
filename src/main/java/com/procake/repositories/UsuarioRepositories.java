package com.procake.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procake.v1.models.GrupoAcessoModel;
import com.procake.v1.models.UsuarioModel;

public interface UsuarioRepositories extends JpaRepository<UsuarioModel, UUID> {

	Optional<UsuarioModel> findByUsername(String username);
	
	List<UsuarioModel> findByGrupoAcesso(GrupoAcessoModel grupoAcesso);
	
	@Query("SELECT U FROM UsuarioModel U WHERE U.enabled = TRUE")
	Page<UsuarioModel> listarTodosAtivos(Pageable pageable);
	
	@Query("SELECT U FROM UsuarioModel U WHERE U.enabled = FALSE")
	Page<UsuarioModel> listarTodosDesativados(Pageable pageable);
	
	@Query("SELECT U FROM UsuarioModel U WHERE U.nome LIKE %:nome%")
	List<UsuarioModel> buscarPorNome(String nome);
}