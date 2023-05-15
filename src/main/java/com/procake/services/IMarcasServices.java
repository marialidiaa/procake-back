package com.procake.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.MarcaDTO;

@Service
public interface IMarcasServices {

	public Page<MarcaDTO> listarTodos(Pageable pageable);
	
	public Page<MarcaDTO> listarTodosAtivos(Pageable pageable);

	public Page<MarcaDTO> listarTodosDesativados(Pageable pageable);

	public MarcaDTO buscarPorId(UUID id);

	public List<MarcaDTO> buscarPorNome(String nome);

	public MarcaDTO inserir(MarcaDTO marca);

	public MarcaDTO atualizar(UUID id, MarcaDTO marca);

	public List<MarcaDTO> buscarAtivoPorNome(String nome);

}
