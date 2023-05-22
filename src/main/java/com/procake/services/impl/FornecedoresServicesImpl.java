package com.procake.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.FornecedoresRepositories;
import com.procake.services.IFornecedoresServices;
import com.procake.utils.ToUpper;
import com.procake.v1.dtos.FornecedoresDTO;
import com.procake.v1.models.FornecedoresModel;

@Component
public class FornecedoresServicesImpl implements IFornecedoresServices{
	
	private FornecedoresRepositories repository;

	public FornecedoresServicesImpl(FornecedoresRepositories repository) {
		this.repository = repository;
	}

	@Override
	public Page<FornecedoresDTO> listarTodos(Pageable pageable) {
		Page<FornecedoresModel> page = repository.findAll(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.fornecedores2FornecedoresDTO(i));
	}

	@Override
	public Page<FornecedoresDTO> listarTodosAtivos(Pageable pageable) {
		Page<FornecedoresModel> page = repository.listarTodosAtivos(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.fornecedores2FornecedoresDTO(i));
	}

	@Override
	public Page<FornecedoresDTO> listarTodosDesativados(Pageable pageable) {
		Page<FornecedoresModel> page = repository.listarTodosDesativados(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.fornecedores2FornecedoresDTO(i));
	}

	@Override
	public FornecedoresDTO buscarPorId(UUID id) {
		return repository.findById(id).map(u -> SimpleMapper.INSTANCE.fornecedores2FornecedoresDTO(u))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado com esse id: " + id));
	}

	@Override
	public List<FornecedoresDTO> buscarPorNome(String nome) {
		if(nome.equals("undefined")) {
			nome = " ";
		}

		List<FornecedoresModel> listMarca = repository.buscarPorNome(nome.trim().toUpperCase());
		List<FornecedoresDTO> list = new ArrayList<>();	
		 listMarca.forEach(item -> list.add(SimpleMapper.INSTANCE.fornecedores2FornecedoresDTO(item)));
		return list;
	}

	@Override
	public FornecedoresDTO inserir(FornecedoresDTO fornecedores) {
		FornecedoresModel model = SimpleMapper.INSTANCE.fornecedoresDTO2Fornecedores(fornecedores);
		
		ToUpper.UPPER_FORNECEDORES_MODEL(model);
		model = repository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.fornecedores2FornecedoresDTO(model);
	}

	@Override
	public FornecedoresDTO atualizar(UUID id, FornecedoresDTO fornecedores) {
		FornecedoresModel model = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor não encontrado com esse id: " + id));

		model.setBairro(fornecedores.getBairro());
		model.setCep(fornecedores.getCep());
		model.setCidade(fornecedores.getCidade());
		model.setComplemento(fornecedores.getComplemento());
		model.setCpfCnpj(fornecedores.getCpfCnpj());
		model.setEmail(fornecedores.getEmail());
		model.setEstado(fornecedores.getEstado());
		model.setNome(fornecedores.getNome());
		model.setNumero(fornecedores.getNumero());
		model.setRua(fornecedores.getRua());
		model.setTelefone(fornecedores.getTelefone());
		ToUpper.UPPER_FORNECEDORES_MODEL(model);
		model = repository.saveAndFlush(model);	

		return SimpleMapper.INSTANCE.fornecedores2FornecedoresDTO(model);
	}

	@Override
	public List<FornecedoresDTO> buscarAtivoPorNome(String nome) {
		if(nome.equals("undefined")) {
			nome = " ";
		}

		List<FornecedoresModel> listUser = repository.buscarAtivoPorNome(nome.trim().toUpperCase());
		List<FornecedoresDTO> list = new ArrayList<>();	
		 listUser.forEach(item -> list.add(SimpleMapper.INSTANCE.fornecedores2FornecedoresDTO(item)));
		return list;
	}
	
	

}
