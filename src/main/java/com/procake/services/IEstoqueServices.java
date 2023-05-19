package com.procake.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.EstoqueCompletoDTO;
import com.procake.v1.dtos.EstoqueCompletoPesquisaDTO;
import com.procake.v1.dtos.EstoqueDTO;
import com.procake.v1.models.EstoqueModel;

@Service
public interface IEstoqueServices {

	public Page<EstoqueDTO> listarTodos(Pageable pageable);

	public EstoqueDTO buscarPorID(UUID id);
	
	public List<EstoqueModel> listarPorIdInsumo(UUID id);

	public List<EstoqueDTO> buscarPorNomeAtivo(String nome);

	public EstoqueDTO inserir(EstoqueDTO insumo);

	public List<EstoqueCompletoDTO> buscaCompleta(EstoqueCompletoPesquisaDTO estoque);
	
	public EstoqueCompletoDTO estornar(UUID id);

}
