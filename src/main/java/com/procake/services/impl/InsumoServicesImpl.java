package com.procake.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.procake.exceptions.OperacaoInvalidaException;
import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.EstoqueRespositories;
import com.procake.repositories.InsumoRepositories;
import com.procake.services.IInsumoServices;
import com.procake.utils.ToUpper;
import com.procake.v1.dtos.InsumoDTO;
import com.procake.v1.models.EstoqueModel;
import com.procake.v1.models.InsumoModel;

@Component
public class InsumoServicesImpl implements IInsumoServices{
	
	private InsumoRepositories repository;
	private EstoqueRespositories estoqueRepository;
	
	public InsumoServicesImpl(InsumoRepositories repository, EstoqueRespositories estoqueRepository) {
		this.repository = repository;
		this.estoqueRepository = estoqueRepository;
	}

	@Override
	public InsumoDTO buscarPorId(UUID id) {
		return repository.findById(id).map(u -> SimpleMapper.INSTANCE.insumo2InsumoDTO(u))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Insumo não encontrado com esse id: " + id));
	}

	@Override
	public Page<InsumoDTO> listarTodos(Pageable pageable) {
		Page<InsumoModel> page = repository.findAll(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.insumo2InsumoDTO(i));
	}
	
	@Override
	public Page<InsumoDTO> listarTodosAtivos(Pageable pageable) {
		Page<InsumoModel> page = repository.listarTodosAtivos(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.insumo2InsumoDTO(i));
	}
	
	@Override
	public Page<InsumoDTO> listarTodosDesativados(Pageable pageable) {
		Page<InsumoModel> page = repository.listarTodosDesativados(pageable);
		return page.map(i -> SimpleMapper.INSTANCE.insumo2InsumoDTO(i));
	}

	@Override
	public List<InsumoDTO> buscarPorNome(String nome) {
		
		if(nome.equals("undefined")) {
			nome = " ";
		}

		List<InsumoModel> listUser = repository.buscarPorNome(nome.trim().toUpperCase());
		List<InsumoDTO> list = new ArrayList<>();	
		 listUser.forEach(item -> list.add(SimpleMapper.INSTANCE.insumo2InsumoDTO(item)));
		return list;
	}

	@Override
	public InsumoDTO inserir(InsumoDTO insumoDTO) {
		
		InsumoModel model = SimpleMapper.INSTANCE.insumoDTO2Insumo(insumoDTO);
		
		ToUpper.UPPER_INSUMO_MODEL(model);
		model = repository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.insumo2InsumoDTO(model);
	}

	@Override
	public InsumoDTO atualizar(UUID id, InsumoDTO insumo) {
	
		InsumoModel model = repository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Insumo não encontrado com esse id: " + id));

		//Atualização de status do insumo
		if(insumo.isEnabled() != model.isEnabled()) {
			List<EstoqueModel> estoque = estoqueRepository.buscarPorInsumoAtivo(model.getId());
			
			if(estoque.size() > 0) {
				throw new OperacaoInvalidaException("Para desativar o insumo é preciso que o estoque dele esteja zerado");
			}
		}

		model.setNome(insumo.getNome());
		model.setEnabled(insumo.isEnabled());
		model.setDescricao(insumo.getDescricao());
		ToUpper.UPPER_INSUMO_MODEL(model);
		model = repository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.insumo2InsumoDTO(model);
	}

	@Override
	public List<InsumoDTO> buscarAtivoPorNome(String nome) {
		if(nome.equals("undefined")) {
			nome = " ";
		}

		List<InsumoModel> listUser = repository.buscarAtivoPorNome(nome.trim().toUpperCase());
		List<InsumoDTO> list = new ArrayList<>();	
		 listUser.forEach(item -> list.add(SimpleMapper.INSTANCE.insumo2InsumoDTO(item)));
		return list;
	}
}