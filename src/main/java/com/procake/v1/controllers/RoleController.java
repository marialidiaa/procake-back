package com.procake.v1.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.procake.services.IRolesServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.RoleDTO;

@Controller
@RequestMapping("/api/v1/roles")
public class RoleController {

	private IRolesServices service;

	Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	public RoleController(IRolesServices service) {
		this.service = service;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<Page<RoleDTO>> listarTodos(
			@PageableDefault(page = 0, size = 1, sort = "id", direction = Direction.ASC) Pageable pageable) {
		
		logger.warn("Listar todas as roles");
		
		return ResponseEntity.ok().body(service.listarTodos(pageable));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<RoleDTO> buscaPorId(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando rola por ID: " + id);
		
		return ResponseEntity.ok().body(service.buscarPorId(UUID.fromString(id)));
	}
}
