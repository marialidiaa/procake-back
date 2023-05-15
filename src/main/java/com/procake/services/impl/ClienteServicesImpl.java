package com.procake.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.ClienteRepositories;
import com.procake.services.IClienteServices;
import com.procake.utils.ToUpper;
import com.procake.v1.dtos.ClienteDTO;
import com.procake.v1.models.ClienteModel;

@Component
public class ClienteServicesImpl implements IClienteServices{
	
	private ClienteRepositories repository;

	public ClienteServicesImpl(ClienteRepositories repository) {
		this.repository = repository;
	}

	@Override
	public Page<ClienteDTO> listarTodos(Pageable pageable) {
		Page<ClienteModel> page = repository.findAll(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.cliente2ClienteDTO(i));
	}

	@Override
	public Page<ClienteDTO> listarTodosAtivos(Pageable pageable) {
		Page<ClienteModel> page = repository.listarTodosAtivos(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.cliente2ClienteDTO(i));
	}

	@Override
	public Page<ClienteDTO> listarTodosDesativados(Pageable pageable) {
		Page<ClienteModel> page = repository.listarTodosDesativados(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.cliente2ClienteDTO(i));
	}

	@Override
	public ClienteDTO buscarPorId(UUID id) {
		return repository.findById(id).map(u -> SimpleMapper.INSTANCE.cliente2ClienteDTO(u))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com esse id: " + id));
	}

	@Override
	public List<ClienteDTO> buscarPorNome(String nome) {
		if(nome.equals("undefined")) {
			nome = " ";
		}

		List<ClienteModel> listMarca = repository.buscarPorNome(nome.trim().toUpperCase());
		List<ClienteDTO> list = new ArrayList<>();	
		 listMarca.forEach(item -> list.add(SimpleMapper.INSTANCE.cliente2ClienteDTO(item)));
		return list;
	}

	@Override
	public ClienteDTO inserir(ClienteDTO cliente) {
	ClienteModel model = SimpleMapper.INSTANCE.clienteDTO2cliente(cliente);
		
		ToUpper.UPPER_CLIENTE_MODEL(model);
		model = repository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.cliente2ClienteDTO(model);
	}

	@Override
	public ClienteDTO atualizar(UUID id, ClienteDTO cliente) {
		ClienteModel model = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado com esse id: " + id));

		model.setNome(cliente.getNome());
		model.setEnabled(cliente.getEnabled());
		model.setBairro(cliente.getBairro());
		model.setCidade(cliente.getCidade());
		model.setComplemento(cliente.getComplemento());
		model.setRua(cliente.getRua());
		model.setEmail(cliente.getEmail());
		ToUpper.UPPER_CLIENTE_MODEL(model);
		model = repository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.cliente2ClienteDTO(model);
	}

	@Override
	public List<ClienteDTO> buscarAtivoPorNome(String nome) {
		if(nome.equals("undefined")) {
			nome = " ";
		}

		List<ClienteModel> listUser = repository.buscarAtivoPorNome(nome.trim().toUpperCase());
		List<ClienteDTO> list = new ArrayList<>();	
		 listUser.forEach(item -> list.add(SimpleMapper.INSTANCE.cliente2ClienteDTO(item)));
		return list;
	}
	
	

}
