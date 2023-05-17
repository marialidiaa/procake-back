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

import com.procake.services.IEstoqueServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.EstoqueCompletoDTO;
import com.procake.v1.dtos.EstoqueCompletoPesquisaDTO;
import com.procake.v1.dtos.EstoqueDTO;
import com.procake.v1.models.EstoqueModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/estoque")
public class EstoqueController {
	
	private IEstoqueServices service;
		
	public EstoqueController(IEstoqueServices services) {
		this.service = services;
	}

	Logger logger = LoggerFactory.getLogger(EstoqueController.class);
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<EstoqueDTO>> listarTodosAtivos(
			@PageableDefault(page = 0, size = 1, sort = "insumo.nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todo estoque ativo");

		return ResponseEntity.ok().body(service.listarTodos(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<EstoqueDTO> buscarPorID(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando estoque com o ID" + id);
		
		return ResponseEntity.ok().body(service.buscarPorID(UUID.fromString(id)));
	}
	
	@GetMapping(value = "listar-insumo-id/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<EstoqueModel>> buscarPorInsumoID(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando estoque pelo insumo com o ID" + id);
		
		return ResponseEntity.ok().body(service.listarPorIdInsumo(UUID.fromString(id)));
	}
	
	@GetMapping(value = "pesquisar/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<EstoqueDTO>> pesquisarPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando insumo por nome: " + nome);

		return ResponseEntity.ok().body(service.buscarPorNomeAtivo(nome));
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<EstoqueDTO> inserir(@Valid @RequestBody EstoqueDTO estoque) {
		
		logger.warn("Inserindo novo estoque");
	
		EstoqueDTO estoqueDTO = service.inserir(estoque);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estoqueDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(estoqueDTO);
	}
	
	@PostMapping(value = "pesquisar", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<EstoqueCompletoDTO>> pesquisaCompleta(@Valid @RequestBody EstoqueCompletoPesquisaDTO estoque) {
		
		logger.warn("Buscando insumos por varios nomes: " + estoque);

		return ResponseEntity.ok().body(service.buscaCompleta(estoque));
	}
	
	@PostMapping(value = "estorno", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<EstoqueCompletoDTO> estorno(@RequestBody EstoqueCompletoDTO estoque) {
		
		logger.warn("estorno estoque com o ID: " + estoque.getId());

		return ResponseEntity.ok().body(service.estornar(estoque.getId()));
	}
}
