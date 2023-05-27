package com.procake.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.procake.v1.dtos.LancamentoDTO;
import com.procake.v1.models.LancamentoModel;

@Service
public interface ILancamentoServices {
	
	public List<LancamentoDTO> listarNotaFiscalId(UUID id);

	public List<LancamentoModel> listarPorIdInsumo(UUID id);

	public LancamentoDTO buscarPorId(UUID fromString);

}
