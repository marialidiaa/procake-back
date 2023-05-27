package com.procake.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.NotaFiscalDTO;
import com.procake.v1.dtos.NotaFiscalPesquisaDTO;

@Service
public interface INotaFiscalServices {

	public Page<NotaFiscalDTO> listarTodos(Pageable pageable);

	public Page<NotaFiscalDTO> listarTodosLancados(Pageable pageable);

	public Page<NotaFiscalDTO> listarTodosModificados(Pageable pageable);

	public NotaFiscalDTO buscarPorId(UUID id);

	NotaFiscalDTO inserir(NotaFiscalDTO nota);

	List<NotaFiscalDTO> buscaCompleta(NotaFiscalPesquisaDTO nota);

}
