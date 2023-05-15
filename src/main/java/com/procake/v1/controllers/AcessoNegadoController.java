package com.procake.v1.controllers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.procake.exceptions.ErroPadrao;
import com.procake.utils.MediaType;

@Controller
@RequestMapping("/acess-denied")
public class AcessoNegadoController {

	@GetMapping(produces = { MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML }, value = "/{protocol}/{version}/{resource}")
	public ResponseEntity<ErroPadrao> acessoNegado(@PathVariable(value = "protocol") String protocol,
			@PathVariable(value = "version") String version, @PathVariable(value = "resource") String resource) {

		String path = "/" + protocol + "/" + resource;

		ErroPadrao err = new ErroPadrao(Instant.now(), 403, "Acesso insuficente",
				"Acesso negado para este recurso, entre em contato com o administrador do sistema", path);

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON,
			MediaType.APPLICATION_XML }, value = "/{protocol}/{version}/{resource}/{id}")
	public ResponseEntity<ErroPadrao> acessoNegadoComID(@PathVariable(value = "protocol") String protocol,
			@PathVariable(value = "version") String version, @PathVariable(value = "resource") String resource,
			@PathVariable(value = "id") String id) {

		String path = "/" + protocol + "/" + resource + "/" + id;

		ErroPadrao err = new ErroPadrao(Instant.now(), 403, "Acesso insuficente",
				"Acesso negado para este recurso, entre em contato com o administrador do sistema", path);

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public ResponseEntity<ErroPadrao> usuarioDestaivado() {

		ErroPadrao err = new ErroPadrao(Instant.now(), 403, "Acesso insuficente",
				"Acesso negado para este recurso, entre em contato com o administrador do sistema", null);

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}
