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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.procake.services.INotaFiscalServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.NotaFiscalDTO;
import com.procake.v1.dtos.NotaFiscalPesquisaDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/nota-fiscal")
public class NotaFiscalController {
	
	private INotaFiscalServices service;

	Logger logger = LoggerFactory.getLogger(NotaFiscalController.class);
	
	public NotaFiscalController(INotaFiscalServices service) {
		this.service = service;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<NotaFiscalDTO>> listarTodos(
			@PageableDefault(page = 0, size = 100, sort = "dataInsercao", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos as notas fiscais");
		
		return ResponseEntity.ok().body(service.listarTodos(pageable));
	}
	
	@GetMapping(path = "/lancados", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<NotaFiscalDTO>> listarTodosLancados(
			@PageableDefault(page = 0, size = 100, sort = "dataInsercao", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos as notas fiscais lancadas");
		
		return ResponseEntity.ok().body(service.listarTodosLancados(pageable));
	}
	
	@GetMapping(path = "/modificado", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<NotaFiscalDTO>> listarTodosModificados(
			@PageableDefault(page = 0, size = 100, sort = "dataInsercao", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos as notas fiscais modificadas");
		
		return ResponseEntity.ok().body(service.listarTodosModificados(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<NotaFiscalDTO> buscarPorId(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando nota fiscal por ID: " + id);

		return ResponseEntity.ok().body(service.buscarPorId(UUID.fromString(id)));
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<NotaFiscalDTO> inserir(@Valid @RequestBody NotaFiscalDTO nota) {
		
		logger.warn("Inserindo nova nota fiscal");
	
		NotaFiscalDTO notaDTO = service.inserir(nota);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(notaDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(notaDTO);
	}
	
	@PostMapping(value = "/busca-completa", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<NotaFiscalDTO>> buscaCompleta(@Valid @RequestBody NotaFiscalPesquisaDTO nota) {
		
		logger.warn("Pesquisa completa de nota fiscal");

		return ResponseEntity.ok().body(service.buscaCompleta(nota));
	}
}
