package com.procake.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.LancamentoRepositories;
import com.procake.services.ILancamentoServices;
import com.procake.v1.dtos.LancamentoDTO;
import com.procake.v1.models.LancamentoModel;

@Component
public class LancamentoServicesImpl implements ILancamentoServices {
	
	
	private LancamentoRepositories repository;

	public LancamentoServicesImpl(LancamentoRepositories repository) {
		this.repository = repository;
	}

	@Override
	public List<LancamentoDTO> listarNotaFiscalId(UUID id) {
		List<LancamentoModel> listLancamento = repository.buscarPorNotaFiscalId(id);
		List<LancamentoDTO> list = new ArrayList<>();	
		 listLancamento.forEach(item -> list.add(SimpleMapper.INSTANCE.lancamento2LancamentoDTO(item)));
		return list;
	}

	@Override
	public List<LancamentoModel> listarPorIdInsumo(UUID id) {
		return repository.buscarPorInsumoID(id);
	}
	
	@Override
	public LancamentoDTO buscarPorId(UUID id) {
		return repository.findById(id).map(u -> SimpleMapper.INSTANCE.lancamento2LancamentoDTO(u))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Lancamento não encontrado com esse id: " + id));
	}
	
}
