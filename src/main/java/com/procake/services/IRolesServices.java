package com.procake.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.procake.v1.dtos.RoleDTO;

@Service
public interface IRolesServices {
	
	public Page<RoleDTO> listarTodos(Pageable pageable);
	
	public RoleDTO buscarPorId(UUID id);
}
