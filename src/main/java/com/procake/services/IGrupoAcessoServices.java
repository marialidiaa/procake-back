package com.procake.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.GrupoAcessoDTO;

@Service
public interface IGrupoAcessoServices {

	public Page<GrupoAcessoDTO> listarTodos(Pageable pageable);

	public GrupoAcessoDTO buscarPorID(UUID fromString);

	public GrupoAcessoDTO inserir(GrupoAcessoDTO accessGroupDTO);

	public GrupoAcessoDTO atualizar(UUID id, GrupoAcessoDTO accessGroupDTO);

	public String deletar(UUID id);

}