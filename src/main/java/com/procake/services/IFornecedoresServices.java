package com.procake.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.FornecedoresDTO;

@Service
public interface IFornecedoresServices {
public Page<FornecedoresDTO> listarTodos(Pageable pageable);
	
	public Page<FornecedoresDTO> listarTodosAtivos(Pageable pageable);

	public Page<FornecedoresDTO> listarTodosDesativados(Pageable pageable);

	public FornecedoresDTO buscarPorId(UUID id);

	public List<FornecedoresDTO> buscarPorNome(String nome);

	public FornecedoresDTO inserir(FornecedoresDTO marca);

	public FornecedoresDTO atualizar(UUID id, FornecedoresDTO marca);

	public List<FornecedoresDTO> buscarAtivoPorNome(String nome);

}
