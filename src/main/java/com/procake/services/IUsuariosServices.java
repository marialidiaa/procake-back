package com.procake.services;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.UsuarioDTO;

@Service
public interface IUsuariosServices {

	public Page<UsuarioDTO> listarTodos(Pageable pageable);
	
	public Page<UsuarioDTO> listarTodosAtivos(Pageable pageable);
	
	public Page<UsuarioDTO> listarTodosDesativados(Pageable pageable);

	public UsuarioDTO buscarPorId(UUID id);
	
	public UsuarioDTO inserir(UsuarioDTO request);
	
	public UsuarioDTO atualizar(UUID id, UsuarioDTO user);

	public UsuarioDTO alterarSenha(UUID id, String password);

	public List<UsuarioDTO> buscarPorNome(String name);
}
