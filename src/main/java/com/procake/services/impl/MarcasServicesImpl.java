package com.procake.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.MarcaRepositories;
import com.procake.services.IMarcasServices;
import com.procake.utils.ToUpper;
import com.procake.v1.dtos.MarcaDTO;
import com.procake.v1.models.MarcaModel;

@Component
public class MarcasServicesImpl implements IMarcasServices{
	
	private MarcaRepositories repository;

	public MarcasServicesImpl(MarcaRepositories repository) {
		this.repository = repository;
	}

	@Override
	public Page<MarcaDTO> listarTodos(Pageable pageable) {
		Page<MarcaModel> page = repository.findAll(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.marca2MarcaDTO(i));
	}

	@Override
	public Page<MarcaDTO> listarTodosAtivos(Pageable pageable) {
		Page<MarcaModel> page = repository.listarTodosAtivos(pageable);
		return page.map(e -> SimpleMapper.INSTANCE.marca2MarcaDTO(e));
	}

	@Override
	public Page<MarcaDTO> listarTodosDesativados(Pageable pageable) {
		Page<MarcaModel> page = repository.listarTodosDesativados(pageable);
		return page.map(e -> SimpleMapper.INSTANCE.marca2MarcaDTO(e));
	}

	@Override
	public MarcaDTO buscarPorId(UUID id) {
		return repository.findById(id).map(u -> SimpleMapper.INSTANCE.marca2MarcaDTO(u))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Marca não encontrada com esse id: " + id));
	}

	@Override
	public List<MarcaDTO> buscarPorNome(String nome) {
		if(nome.equals("undefined")) {
			nome = " ";
		}

		List<MarcaModel> listMarca = repository.buscarPorNome(nome.trim().toUpperCase());
		List<MarcaDTO> list = new ArrayList<>();	
		 listMarca.forEach(item -> list.add(SimpleMapper.INSTANCE.marca2MarcaDTO(item)));
		return list;
	}

	@Override
	public MarcaDTO inserir(MarcaDTO marca) {
		MarcaModel model = SimpleMapper.INSTANCE.marcaDTO2Marca(marca);
		
		ToUpper.UPPER_MARCA_MODEL(model);
		model.setEnabled(true);
		model = repository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.marca2MarcaDTO(model);
	}

	@Override
	public MarcaDTO atualizar(UUID id, MarcaDTO marca) {
		MarcaModel model = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Marca não encontrada com esse id: " + id));

		model.setNome(marca.getNome());
		model.setEnabled(marca.isEnabled());
		ToUpper.UPPER_MARCA_MODEL(model);
		model = repository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.marca2MarcaDTO(model);
	}

	@Override
	public List<MarcaDTO> buscarAtivoPorNome(String nome) {
		if(nome.equals("undefined")) {
			nome = " ";
		}

		List<MarcaModel> listUser = repository.buscarAtivoPorNome(nome.trim().toUpperCase());
		List<MarcaDTO> list = new ArrayList<>();	
		 listUser.forEach(item -> list.add(SimpleMapper.INSTANCE.marca2MarcaDTO(item)));
		return list;
	
	}
}
