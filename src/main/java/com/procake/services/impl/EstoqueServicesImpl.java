package com.procake.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.EstoqueRespositories;
import com.procake.repositories.UsuarioRepositories;
import com.procake.services.IEstoqueServices;
import com.procake.utils.ToUpper;
import com.procake.utils.TokenUtils;
import com.procake.v1.dtos.EstoqueCompletoDTO;
import com.procake.v1.dtos.EstoqueCompletoPesquisaDTO;
import com.procake.v1.dtos.EstoqueDTO;
import com.procake.v1.models.EstoqueModel;
import com.procake.v1.models.UsuarioModel;
import com.procake.v1.models.enums.Tipo;

@Component
public class EstoqueServicesImpl implements IEstoqueServices {
	
	Logger logger = LoggerFactory.getLogger(EstoqueServicesImpl.class);

	private EstoqueRespositories estoqueRepository;
	private UsuarioRepositories usuarioRepository;

	public EstoqueServicesImpl(EstoqueRespositories estoqueRepository, UsuarioRepositories usuarioRepository) {
		this.estoqueRepository = estoqueRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Page<EstoqueDTO> listarTodos(Pageable pageable) {
		Page<EstoqueModel> page = estoqueRepository.findAllEnabled(pageable);
		return page.map(e -> SimpleMapper.INSTANCE.estoque2EstoqueDTO(e));
	}

	@Override
	public EstoqueDTO buscarPorID(UUID id) {
		return estoqueRepository.findById(id).map(e -> SimpleMapper.INSTANCE.estoque2EstoqueDTO(e))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Estoque não encontrado com esse id: " + id));
	} 

	@Override
	public List<EstoqueDTO> buscarPorNomeAtivo(String nome) {

		if (nome.equals("undefined")) {
			nome = " ";
		}
		List<EstoqueModel> listEstoque = estoqueRepository.buscarPorNomeAtivo(nome.trim().toUpperCase());
		List<EstoqueDTO> list = new ArrayList<>();
		listEstoque.forEach(item -> list.add(SimpleMapper.INSTANCE.estoque2EstoqueDTO(item)));
		return list;
	}

	@Override
	public EstoqueDTO inserir(EstoqueDTO estoque) {
			
		UsuarioModel usuario = usuarioRepository.findByUsername(TokenUtils.buscarUsuario().toUpperCase()).get();
		EstoqueModel model = SimpleMapper.INSTANCE.estoqueDTO2Estoque(estoque);
		model.setUsuarioInsercao(usuario);
		model.setDataInsercao(LocalDate.now());
		ToUpper.UPPER_ESTOQUE_MODEL(model);
		model.setTipo(Tipo.LANCADO);
		model = estoqueRepository.save(model);

		return SimpleMapper.INSTANCE.estoque2EstoqueDTO(model);
	}

	@Override
	public List<EstoqueCompletoDTO> buscaCompleta(EstoqueCompletoPesquisaDTO estoque) {
		
		estoque.setInsumo(estoque.getInsumo() == null ? null : estoque.getInsumo().trim().toUpperCase());
		estoque.setUsuarioInsercao(estoque.getUsuarioInsercao() == null ? null : estoque.getUsuarioInsercao().trim().toUpperCase());
		
		if(estoque.getInsumo() == "") {
			estoque.setInsumo(null);
		}
		
		if(estoque.getUsuarioInsercao() == "") {
			estoque.setUsuarioInsercao(null);
		}
		
		logger.warn("Insumo " + estoque.getInsumo());
		logger.warn("Usuario Insercao " + estoque.getUsuarioInsercao());
		logger.warn("Data inicial " + estoque.getDataInicial());
		logger.warn("Data final " + estoque.getDataFinal());
		
		List<EstoqueCompletoDTO> listDTO = new ArrayList<>();
		
		if(estoque.getInsumo() != null && estoque.getUsuarioInsercao() != null && estoque.getDataInicial() != null && estoque.getDataFinal() != null) {
			logger.warn("IF 1");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(estoque.getInsumo(), estoque.getUsuarioInsercao(), estoque.getDataInicial(), estoque.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
	
		if(estoque.getInsumo() == null && estoque.getUsuarioInsercao() != null && estoque.getDataInicial() != null && estoque.getDataFinal() != null) {
			logger.warn("IF 2");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(" ", estoque.getUsuarioInsercao(), estoque.getDataInicial(), estoque.getDataInicial());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() == null && estoque.getUsuarioInsercao() != null && estoque.getDataInicial() == null && estoque.getDataFinal() != null) {
			logger.warn("IF 3");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(" ", estoque.getUsuarioInsercao(), LocalDate.of(1901, 1, 1), estoque.getDataInicial());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() == null && estoque.getUsuarioInsercao() != null && estoque.getDataInicial() != null && estoque.getDataFinal() == null) {
			logger.warn("IF 4");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(" ", estoque.getUsuarioInsercao(), estoque.getDataInicial(), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() != null && estoque.getUsuarioInsercao() == null && estoque.getDataInicial() != null && estoque.getDataFinal() != null) {
			logger.warn("IF 5");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(estoque.getInsumo(), " ", estoque.getDataInicial(), estoque.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() != null && estoque.getUsuarioInsercao() == null && estoque.getDataInicial() == null && estoque.getDataFinal() != null) {
			logger.warn("IF 6");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(estoque.getInsumo(),  " ", LocalDate.of(1901, 1, 1), estoque.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() != null && estoque.getUsuarioInsercao() == null && estoque.getDataInicial() != null && estoque.getDataFinal() == null) {
			logger.warn("IF 7");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(estoque.getInsumo(), estoque.getUsuarioInsercao(), estoque.getDataInicial(), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() != null && estoque.getUsuarioInsercao() != null && estoque.getDataInicial() == null && estoque.getDataFinal() != null) {
			logger.warn("IF 8");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(estoque.getInsumo(), estoque.getUsuarioInsercao(), LocalDate.of(1901, 1, 1), estoque.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() != null && estoque.getUsuarioInsercao() != null && estoque.getDataInicial() != null && estoque.getDataFinal() == null) {
			logger.warn("IF 9");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(estoque.getInsumo(), estoque.getUsuarioInsercao(), estoque.getDataInicial(), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() != null && estoque.getUsuarioInsercao() != null && estoque.getDataInicial() == null && estoque.getDataFinal() == null) {
			logger.warn("IF 10");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoque(estoque.getInsumo(), estoque.getUsuarioInsercao(), LocalDate.of(1901, 1, 1), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() == null && estoque.getUsuarioInsercao() == null && estoque.getDataInicial() != null && estoque.getDataFinal() != null) {
			logger.warn("IF 11");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoquePorData(estoque.getDataInicial(), estoque.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() == null && estoque.getUsuarioInsercao() == null && estoque.getDataInicial() == null && estoque.getDataFinal() != null) {
			logger.warn("IF 12");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoquePorData(LocalDate.of(1901, 1, 1), estoque.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() == null && estoque.getUsuarioInsercao() == null && estoque.getDataInicial() != null && estoque.getDataFinal() == null) {
			logger.warn("IF 13");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoquePorData(estoque.getDataInicial(), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() != null && estoque.getUsuarioInsercao() == null && estoque.getDataInicial() == null && estoque.getDataFinal() == null) {
			logger.warn("IF 14");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoquePorInsumo(estoque.getInsumo());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() == null && estoque.getUsuarioInsercao() != null && estoque.getDataInicial() == null && estoque.getDataFinal() == null) {
			logger.warn("IF 15");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoquePorNomeUsuario(estoque.getUsuarioInsercao());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		if(estoque.getInsumo() == null && estoque.getUsuarioInsercao() == null && estoque.getDataInicial() == null && estoque.getDataFinal() == null) {
			logger.warn("IF 16");
			List<EstoqueModel> list = estoqueRepository.buscarLancamentoEstoquePorData(LocalDate.of(1901, 1, 1), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(item)));
		}
		
		return listDTO;
	}

	@Override
	public EstoqueCompletoDTO estornar(UUID id) {
		
		EstoqueModel model = estoqueRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Estoque não encontrado com esse id: " + id));
		model.setTipo(Tipo.ESTORNADO_LANCAMENTO);
		
		estoqueRepository.save(model);
		
		return SimpleMapper.INSTANCE.estoque2EstoqueCompletoDTO(model);
	}

}