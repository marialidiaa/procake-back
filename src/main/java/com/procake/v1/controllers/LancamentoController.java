package com.procake.v1.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.procake.services.ILancamentoServices;
import com.procake.utils.MediaType;
import com.procake.v1.dtos.LancamentoDTO;

@Controller
@RequestMapping("/api/v1/lancamentos")
public class LancamentoController {

	private ILancamentoServices lancamentoServices;

	public LancamentoController(ILancamentoServices lancamentoServices) {
		this.lancamentoServices = lancamentoServices;
	}
	
	Logger logger = LoggerFactory.getLogger(LancamentoController.class);

	@GetMapping(value = "pesquisar/nota-fiscal-id/{id}", produces = { MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML })
	public ResponseEntity<List<LancamentoDTO>> listarNotaFiscalId(@PathVariable(value = "id") UUID id) {

		logger.warn("Buscando insumo ativo por nome: " + id);

		return ResponseEntity.ok().body(lancamentoServices.listarNotaFiscalId(id));
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<LancamentoDTO> buscarPorId(@PathVariable(value = "id") String id) {
		
		logger.warn("Buscando lancamento por ID: " + id);

		return ResponseEntity.ok().body(lancamentoServices.buscarPorId(UUID.fromString(id)));
	}

}
