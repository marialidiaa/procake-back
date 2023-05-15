package com.procake.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.ClienteDTO;

@Service
public interface IClienteServices {
public Page<ClienteDTO> listarTodos(Pageable pageable);
	
	public Page<ClienteDTO> listarTodosAtivos(Pageable pageable);

	public Page<ClienteDTO> listarTodosDesativados(Pageable pageable);

	public ClienteDTO buscarPorId(UUID id);

	public List<ClienteDTO> buscarPorNome(String nome);

	public ClienteDTO inserir(ClienteDTO cliente);

	public ClienteDTO atualizar(UUID id, ClienteDTO cliente);

	public List<ClienteDTO> buscarAtivoPorNome(String nome);
}
