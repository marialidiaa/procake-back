package com.procake.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.procake.services.IAutenticacaoServices;
import com.procake.services.ITokenServices;
import com.procake.v1.dtos.AuthTokenDTO;
import com.procake.v1.dtos.DadosAutenticacaoDTO;
import com.procake.v1.models.UsuarioModel;

@Component
public class AutenticacaoServicesImpl implements IAutenticacaoServices {

	Logger logger = LoggerFactory.getLogger(AutenticacaoServicesImpl.class);

	private AuthenticationManager authenticationManager;
	private ITokenServices tokenService;

	public AutenticacaoServicesImpl(AuthenticationManager authenticationManager, ITokenServices tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

//	@Transactional
//	public AuthenticationResponse register(RegisterRequest request) {
//		UserModel user = new UserModel(request.getName(), request.getEmail(),
//				passwordEncoder.encode(request.getPassword()), true,
//				new AccessGroupModel(UUID.fromString("d7e51510-cbfd-11ed-afa1-0242ac120002"), "ADMIN"));
//		repository.save(user);
//		String jwt = jwtService.gernerateToken(user);
//		return new AuthenticationResponse(jwt);
//	}

	public AuthTokenDTO autenticar(DadosAutenticacaoDTO request) {
		request.setEmail(request.getEmail().trim().toUpperCase());
		
		UsernamePasswordAuthenticationToken usernamePassworAuthenticationToken = new UsernamePasswordAuthenticationToken(
				request.getEmail(), request.getSenha());
		
		Authentication authenticate = this.authenticationManager.authenticate(usernamePassworAuthenticationToken);
		UsuarioModel usuario = (UsuarioModel) authenticate.getPrincipal();
		AuthTokenDTO token = new AuthTokenDTO(tokenService.buscarToken(usuario));

		return token;
	}
}