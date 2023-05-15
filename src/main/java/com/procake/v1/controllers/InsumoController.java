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

import com.procake.services.IInsumoServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.InsumoDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/insumos")
public class InsumoController {

	private IInsumoServices insumoServices;
	
	public InsumoController(IInsumoServices insumoServices) {
		this.insumoServices = insumoServices;
	}
	
	Logger logger = LoggerFactory.getLogger(InsumoController.class);
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<InsumoDTO>> listarTodos(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os insumos");
		
		return ResponseEntity.ok().body(insumoServices.listarTodos(pageable));
	}
	
	@GetMapping(path = "/ativos", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<InsumoDTO>> listarTodosAtivos(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os insumos ativos");
		
		return ResponseEntity.ok().body(insumoServices.listarTodosAtivos(pageable));
	}
	
	@GetMapping(path = "/desativados", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<InsumoDTO>> listarTodosDesativados(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os insumos desativados");
		
		return ResponseEntity.ok().body(insumoServices.listarTodosDesativados(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<InsumoDTO> buscarPorId(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando insumo por ID: " + id);

		return ResponseEntity.ok().body(insumoServices.buscarPorId(UUID.fromString(id)));
	}
	
	@GetMapping(value = "pesquisar/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<InsumoDTO>> pesquisarPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando insumo por nome: " + nome);

		return ResponseEntity.ok().body(insumoServices.buscarPorNome(nome));
	}
	
	@GetMapping(value = "pesquisar/ativo/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<InsumoDTO>> pesquisarAtivoPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando insumo ativo por nome: " + nome);

		return ResponseEntity.ok().body(insumoServices.buscarAtivoPorNome(nome));
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<InsumoDTO> inserir(@Valid @RequestBody InsumoDTO insumo) {
		
		logger.warn("Inserindo novo insumo");
	
		InsumoDTO insumoDTO = insumoServices.inserir(insumo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(insumoDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(insumoDTO);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<InsumoDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody InsumoDTO insumo) {
		
		logger.warn("Atualizando insumo com ID " + id);
		
		return ResponseEntity.ok().body(insumoServices.atualizar(id, insumo));
	}
	
}
