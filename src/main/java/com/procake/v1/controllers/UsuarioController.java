package com.procake.v1.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.procake.services.IUsuariosServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.RedefinirSenhaDTO;
import com.procake.v1.dtos.UsuarioDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	private IUsuariosServices service;

	public UsuarioController(IUsuariosServices service) {
		this.service = service;
	}

	Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<UsuarioDTO>> listarTodos(
			@PageableDefault(page = 0, size = 1, sort = "id", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os usuarios");

		return ResponseEntity.ok().body(service.listarTodos(pageable));
	}
	
	@GetMapping(path = "/ativos", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<UsuarioDTO>> listarTodosAtivos(
			@PageableDefault(page = 0, size = 1, sort = "id", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os usuarios ativos");

		return ResponseEntity.ok().body(service.listarTodosAtivos(pageable));
	}
	
	@GetMapping(path = "/desativados", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<UsuarioDTO>> listarTodosDesativados(
			@PageableDefault(page = 0, size = 1, sort = "id", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os usuarios desativados");

		return ResponseEntity.ok().body(service.listarTodosDesativados(pageable));
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando um usuario por ID: " + id);

		return ResponseEntity.ok().body(service.buscarPorId(UUID.fromString(id)));
	}
	
	@GetMapping(value = "pesquisar/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<UsuarioDTO>> pesquisarPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando usuario por nome: " + nome);

		return ResponseEntity.ok().body(service.buscarPorNome(nome));
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<UsuarioDTO> inserir(@Valid @RequestBody UsuarioDTO usuario) {
		
		logger.warn("Inserir usuario");
	
		UsuarioDTO usuarioDTO = service.inserir(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
		
		logger.warn("Atualizando usuario");
		
		return ResponseEntity.ok().body(service.atualizar(id, usuarioDTO));
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/redefinir-senha/{id}")
	public ResponseEntity<UsuarioDTO> redefinirSenha(@PathVariable UUID id, @Valid @RequestBody RedefinirSenhaDTO request) {		
		
		logger.warn("Redefinir senha");
		
		return ResponseEntity.ok().body(service.alterarSenha(id, request.getSenha()));
	
	}
}