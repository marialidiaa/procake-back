package com.procake.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.procake.services.ITokenServices;
import com.procake.v1.models.UsuarioModel;

@Component
public class TokenServicesImpl implements ITokenServices{
	
	@Value(value = "${application.jwt.secret-key:default}")
	private String secretKey = "bolo_de_cenoura";
	
	@Value(value = "${application.jwt.expiration-minutes:default}")
	private Integer expirationMinutes = 60;
	
	@Value(value = "${application.jwt.issuer:default}")
	private String issuer = "login";

	@Override
	public String buscarToken(UsuarioModel user) {
		return JWT.create()
				.withIssuer(issuer)
				.withSubject(user.getUsername())
				.withClaim("id", user.getId().toString())
				.withExpiresAt(LocalDateTime.now()
						.plusMinutes(expirationMinutes)
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256(secretKey));
	}

	@Override
	public String buscarSubject(String token) {
		return JWT.require(Algorithm.HMAC256(secretKey)).withIssuer(issuer).build().verify(token).getSubject();
	}

}