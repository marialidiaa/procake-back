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

import com.procake.services.IMarcasServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.MarcaDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/marcas")
public class MarcaController {
	private IMarcasServices service;

	Logger logger = LoggerFactory.getLogger(MarcaController.class);
	
	public MarcaController(IMarcasServices service) {
		this.service = service;
	}
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<MarcaDTO>> listarTodos(
			@PageableDefault(page = 0, size = 100, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos as Marcas");
		
		return ResponseEntity.ok().body(service.listarTodos(pageable));
	}
	
	@GetMapping(path = "/desativados", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<MarcaDTO>> listarTodosDesativados(
			@PageableDefault(page = 0, size = 100, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos as marcas desativadas");
		
		return ResponseEntity.ok().body(service.listarTodosDesativados(pageable));
	}
	
	@GetMapping(path = "/ativas", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<MarcaDTO>> listarTodosAtivos(
			@PageableDefault(page = 0, size = 100, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos as marcas ativas");
		
		return ResponseEntity.ok().body(service.listarTodosAtivos(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<MarcaDTO> buscarPorId(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando marca por ID: " + id);

		return ResponseEntity.ok().body(service.buscarPorId(UUID.fromString(id)));
	}
	
	@GetMapping(value = "pesquisar/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<MarcaDTO>> pesquisarPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando marca por nome: " + nome);

		return ResponseEntity.ok().body(service.buscarPorNome(nome));
	}
	
	@GetMapping(value = "pesquisar/ativo/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<MarcaDTO>> pesquisarAtivoPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando marca ativa por nome: " + nome);

		return ResponseEntity.ok().body(service.buscarAtivoPorNome(nome));
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<MarcaDTO> inserir(@Valid @RequestBody MarcaDTO marca) {
		
		logger.warn("Inserindo nova marca");
	
		MarcaDTO marcaDTO = service.inserir(marca);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marcaDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(marcaDTO);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<MarcaDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody MarcaDTO marca) {
		
		logger.warn("Atualizando marca com ID " + id);
		
		return ResponseEntity.ok().body(service.atualizar(id, marca));
	}
	
}
