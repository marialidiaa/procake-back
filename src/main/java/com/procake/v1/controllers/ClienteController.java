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

import com.procake.services.IClienteServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.ClienteDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping ("api/v1/clientes")
public class ClienteController {
	
	Logger logger = LoggerFactory.getLogger(MarcaController.class);
	
	private IClienteServices service;

	public ClienteController(IClienteServices service) {
		this.service = service;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<ClienteDTO>> listarTodos(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os clientes");
		
		return ResponseEntity.ok().body(service.listarTodos(pageable));
	}
	
	@GetMapping(path = "/desativados", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<ClienteDTO>> listarTodosDesativados(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os clientes desativadas");
		
		return ResponseEntity.ok().body(service.listarTodosDesativados(pageable));
	}
	
	@GetMapping(path = "/ativos", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<ClienteDTO>> listarTodosAtivos(
			@PageableDefault(page = 0, size = 1, sort = "nome", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os clientes ativos");
		
		return ResponseEntity.ok().body(service.listarTodosAtivos(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando cliente por ID: " + id);

		return ResponseEntity.ok().body(service.buscarPorId(UUID.fromString(id)));
	}
	
	@GetMapping(value = "pesquisar/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<ClienteDTO>> pesquisarPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando cliente por nome: " + nome);

		return ResponseEntity.ok().body(service.buscarPorNome(nome));
	}
	
	@GetMapping(value = "pesquisar/ativo/{nome}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<List<ClienteDTO>> pesquisarAtivoPorNome(@PathVariable(value = "nome") String nome) {
		
		logger.warn("Buscando cliente ativo por nome: " + nome);

		return ResponseEntity.ok().body(service.buscarAtivoPorNome(nome));
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<ClienteDTO> inserir(@Valid @RequestBody ClienteDTO cliente) {
		
		logger.warn("Inserindo novo cliente");
	
		ClienteDTO clienteDTO = service.inserir(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(clienteDTO);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody ClienteDTO cliente) {
		
		logger.warn("Atualizando cliente com ID " + id);
		
		return ResponseEntity.ok().body(service.atualizar(id, cliente));
	}
	

}
