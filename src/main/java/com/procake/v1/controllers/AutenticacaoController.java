package com.procake.v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.procake.services.IAutenticacaoServices;
import com.procake.v1.dtos.DadosAutenticacaoDTO;
import com.procake.v1.dtos.AuthTokenDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/v1/auth")
public class AutenticacaoController {

	private final IAutenticacaoServices service;

	public AutenticacaoController(IAutenticacaoServices service) {
		this.service = service;
	}

//	@PostMapping("/register")
//	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
//		return ResponseEntity.ok(service.register(request));
//	}

	@PostMapping("/autenticar")
	public ResponseEntity<AuthTokenDTO> autenticar(@RequestBody @Valid DadosAutenticacaoDTO request) {

		return ResponseEntity.ok(service.autenticar(request));
	}
}