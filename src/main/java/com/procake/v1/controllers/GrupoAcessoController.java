package com.procake.v1.controllers;

import java.net.URI;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.procake.services.IGrupoAcessoServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.GrupoAcessoDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/grupos")
public class GrupoAcessoController {

	private IGrupoAcessoServices service;

	public GrupoAcessoController(IGrupoAcessoServices service) {
		this.service = service;
	}

	Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<GrupoAcessoDTO>> listarTodos(
			@PageableDefault(page = 0, size = 1, sort = "id", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listando todos os grupos de acesso");
		
		return ResponseEntity.ok().body(service.listarTodos(pageable));
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<GrupoAcessoDTO> buscarPorID(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando grupo de acesso com o ID" + id);
		
		return ResponseEntity.ok().body(service.buscarPorID(UUID.fromString(id)));
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<GrupoAcessoDTO> inserir(@Valid @RequestBody GrupoAcessoDTO accessGroupDTO) {
		
		logger.warn("Inserir grupo de acesso");

		accessGroupDTO = service.inserir(accessGroupDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(accessGroupDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(accessGroupDTO);
	}
	
	@PutMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, consumes = {
			MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<GrupoAcessoDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody GrupoAcessoDTO accessGroupDTO) {
		
		logger.warn("Atualizando grupo de acesso");
		
		return ResponseEntity.ok().body(service.atualizar(id, accessGroupDTO));
	}
	
	@DeleteMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, value = "/{id}")
	public ResponseEntity<String> deletar(@PathVariable UUID id) {
		
		logger.warn("Deletando grupo de acesso");
		
		return ResponseEntity.ok().body(service.deletar(id));
	}
}