package com.procake.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.InsumoDTO;

@Service
public interface IInsumoServices {

	public Page<InsumoDTO> listarTodos(Pageable pageable);

	public Page<InsumoDTO> listarTodosAtivos(Pageable pageable);

	public Page<InsumoDTO> listarTodosDesativados(Pageable pageable);

	public InsumoDTO buscarPorId(UUID id);

	public List<InsumoDTO> buscarPorNome(String nome);

	public InsumoDTO inserir(InsumoDTO insumo);

	public InsumoDTO atualizar(UUID id, InsumoDTO insumo);

	public List<InsumoDTO> buscarAtivoPorNome(String nome);
}
