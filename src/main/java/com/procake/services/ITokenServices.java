package com.procake.services;

import org.springframework.stereotype.Service;

import com.procake.v1.models.UsuarioModel;

@Service
public interface ITokenServices {

	public String buscarToken(UsuarioModel user);
	public String buscarSubject(String token);
}
