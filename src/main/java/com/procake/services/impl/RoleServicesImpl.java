package com.procake.services.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.RolesRepositories;
import com.procake.services.IRolesServices;
import com.procake.v1.dtos.RoleDTO;
import com.procake.v1.models.RoleModel;

@Component
public class RoleServicesImpl implements IRolesServices {

	private RolesRepositories repository;

	public RoleServicesImpl(RolesRepositories repository) {
		this.repository = repository;
	}

	@Override
	public Page<RoleDTO> listarTodos(Pageable pageable) {
		Page<RoleModel> page = repository.findAll(pageable);
		return page.map(r -> SimpleMapper.INSTANCE.role2RoleDTO(r));
	}

	@Override
	public RoleDTO buscarPorId(UUID id) {
		return repository.findById(id).map(r -> SimpleMapper.INSTANCE.role2RoleDTO(r))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Resource not found for this id: " + id));
	}

}
