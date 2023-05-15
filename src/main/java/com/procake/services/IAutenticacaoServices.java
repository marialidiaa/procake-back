package com.procake.services;

import org.springframework.stereotype.Service;

import com.procake.v1.dtos.DadosAutenticacaoDTO;
import com.procake.v1.dtos.AuthTokenDTO;

@Service
public interface IAutenticacaoServices{
		
//	public AuthenticationResponse register(RegisterRequest request);
	
	public AuthTokenDTO autenticar(DadosAutenticacaoDTO request);
}
