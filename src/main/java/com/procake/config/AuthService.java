package com.procake.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.procake.repositories.UsuarioRepositories;

@Service
public class AuthService implements UserDetailsService {

	private UsuarioRepositories repository;

	public AuthService(UsuarioRepositories repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username).get();
	}
}
