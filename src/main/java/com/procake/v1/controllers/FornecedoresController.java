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

import com.procake.services.IFornecedoresServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.FornecedoresDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/fornecedores")
public class FornecedoresController  {
	private IFornecedoresServices service;
	
	Logger logger = LoggerFactory.getLogger(FornecedoresController.class);
	
	public FornecedoresController(IFornecedoresServices service) {
		this.service = service;
	}
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<FornecedoresDTO>> listarTodos(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os Fornecedores");
		
		return ResponseEntity.ok().body(service.listarTodos(pageable));
	}
	
	@GetMapping(path = "/desativados", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<FornecedoresDTO>> listarTodosDesativados(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os fornecedores desativados");
		
		return ResponseEntity.ok().body(service.listarTodosDesativados(pageable));
	}
	
	@GetMapping(path = "/ativos", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<FornecedoresDTO>> listarTodosAtivos(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os fornecedores ativos");
		
		return ResponseEntity.ok().body(service.listarTodosAtivos(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<FornecedoresDTO> buscarPorId(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando fornecedor por ID: " + id);

		return ResponseEntity.ok().body(service.buscarPorId(UUID.fromString(id)));
	}
	
	@GetMapping(value = "pesquisar/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<FornecedoresDTO>> pesquisarPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando fornecedor por nome: " + nome);

		return ResponseEntity.ok().body(service.buscarPorNome(nome));
	}
	
	@GetMapping(value = "pesquisar/ativo/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<FornecedoresDTO>> pesquisarAtivoPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando fornecedor ativo por nome: " + nome);

		return ResponseEntity.ok().body(service.buscarAtivoPorNome(nome));
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<FornecedoresDTO> inserir(@Valid @RequestBody FornecedoresDTO fornecedores) {
		
		logger.warn("Inserindo novo fornecedor");
	
		FornecedoresDTO fornecedoresDTO = service.inserir(fornecedores);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fornecedoresDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(fornecedoresDTO);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<FornecedoresDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody FornecedoresDTO fornecedores) {
		
		logger.warn("Atualizando fornecedor com ID " + id);
		
		return ResponseEntity.ok().body(service.atualizar(id, fornecedores));
	}

}
