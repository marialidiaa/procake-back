package com.procake.config;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.procake.repositories.UsuarioRepositories;
import com.procake.services.ITokenServices;
import com.procake.v1.models.UsuarioModel;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

	private ITokenServices tokenService;
	private UsuarioRepositories userRepository;

	public FilterToken(ITokenServices tokenService, UsuarioRepositories userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token;
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader != null) {

			token = authorizationHeader.replace("Bearer ", "");
			String subject = this.tokenService.buscarSubject(token);
			UsuarioModel user = this.userRepository.findByUsername(subject).get();
			
			if(user.isEnabled() == false) {
				throw new DisabledException("Usuário desativado");
			}
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword(), user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
}
