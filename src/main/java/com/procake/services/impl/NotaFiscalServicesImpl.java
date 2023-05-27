package com.procake.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.LancamentoRepositories;
import com.procake.repositories.NotaFiscalRepositories;
import com.procake.repositories.UsuarioRepositories;
import com.procake.services.INotaFiscalServices;
import com.procake.utils.ToUpper;
import com.procake.utils.TokenUtils;
import com.procake.v1.dtos.NotaFiscalDTO;
import com.procake.v1.dtos.NotaFiscalPesquisaDTO;
import com.procake.v1.models.NotaFiscalModel;
import com.procake.v1.models.UsuarioModel;
import com.procake.v1.models.enums.StatusNotaFiscal;
import com.procake.v1.models.enums.Tipo;

@Component
public class NotaFiscalServicesImpl implements INotaFiscalServices {
	
	private NotaFiscalRepositories repository;
	private UsuarioRepositories userRepository;
	private LancamentoRepositories lancamentoRepositories;

	public NotaFiscalServicesImpl(NotaFiscalRepositories repository, UsuarioRepositories userRepository,
			LancamentoRepositories lancamentoRepositories) {
		this.repository = repository;
		this.userRepository = userRepository;
		this.lancamentoRepositories = lancamentoRepositories;
	}

	@Override
	public Page<NotaFiscalDTO> listarTodos(Pageable pageable) {
		Page<NotaFiscalModel> page = repository.findAll(pageable);
		return page.map(n -> SimpleMapper.INSTANCE.nota2NotaDTO(n));
	}

	@Override
	public Page<NotaFiscalDTO> listarTodosLancados(Pageable pageable) {
		Page<NotaFiscalModel> page = repository.listarTodosLancados(pageable);
		return page.map(n -> SimpleMapper.INSTANCE.nota2NotaDTO(n));
	}

	@Override
	public Page<NotaFiscalDTO> listarTodosModificados(Pageable pageable) {
		Page<NotaFiscalModel> page = repository.listarTodosModificados(pageable);
		return page.map(n -> SimpleMapper.INSTANCE.nota2NotaDTO(n));
	}


	@Override
	public NotaFiscalDTO buscarPorId(UUID id) {
		return repository.findById(id).map(u -> SimpleMapper.INSTANCE.nota2NotaDTO(u))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Nota Fiscal não encontrada com esse id: " + id));
	}

	@Override
	public NotaFiscalDTO inserir(NotaFiscalDTO nota) {
		NotaFiscalModel model = SimpleMapper.INSTANCE.notaDTO2Nota(nota);
		model.setStatus(StatusNotaFiscal.LANCADO);
		model.setDataInsercao(LocalDate.now());
		UsuarioModel usuarioInsercao = userRepository.findByUsername(TokenUtils.buscarUsuario()).get();
		model.setUsuarioInsercao(usuarioInsercao);
		ToUpper.UPPER_NOTA_FISCAL_MODEL(model);
		model = repository.saveAndFlush(model);
				
		for(int i = 0; i < model.getLancamentos().size(); i++) {
			model.getLancamentos().get(i).setNotaFiscal(model);
			model.getLancamentos().get(i).setDataCompra(model.getDataCompra());
			model.getLancamentos().get(i).setDataInsercao(model.getDataInsercao());
			model.getLancamentos().get(i).setFornecedor(model.getFornecedor());
			model.getLancamentos().get(i).setTipo(Tipo.LANCADO);
			model.getLancamentos().get(i).setUsuarioInsercao(usuarioInsercao);
			//colocar o upper aqui
		}
		
		lancamentoRepositories.saveAll(model.getLancamentos());
		return SimpleMapper.INSTANCE.nota2NotaDTO(model);
	}
	
	@Override
	public List<NotaFiscalDTO> buscaCompleta(NotaFiscalPesquisaDTO nota) {
		
		nota.setNotaFiscal(nota.getNotaFiscal() == null ? null : nota.getNotaFiscal().trim().toUpperCase());
		nota.setUsuarioInsercao(nota.getUsuarioInsercao() == null ? null : nota.getUsuarioInsercao().trim().toUpperCase());
		
		if(nota.getNotaFiscal() == "") {
			nota.setNotaFiscal(null);
		}
		
		if(nota.getUsuarioInsercao() == "") {
			nota.setUsuarioInsercao(null);
		}
		
		List<NotaFiscalDTO> listDTO = new ArrayList<>();
		
		if(nota.getNotaFiscal() != null && nota.getUsuarioInsercao() != null && nota.getDataInicial() != null && nota.getDataFinal() != null) {
			List<NotaFiscalModel> list = repository.buscarNota(nota.getNotaFiscal(), nota.getUsuarioInsercao(), nota.getDataInicial(), nota.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
	
		if(nota.getNotaFiscal() == null && nota.getUsuarioInsercao() != null && nota.getDataInicial() != null && nota.getDataFinal() != null) {
			List<NotaFiscalModel> list = repository.buscarNota(" ", nota.getUsuarioInsercao(), nota.getDataInicial(), nota.getDataInicial());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() == null && nota.getUsuarioInsercao() != null && nota.getDataInicial() == null && nota.getDataFinal() != null) {
			List<NotaFiscalModel> list = repository.buscarNota(" ", nota.getUsuarioInsercao(), LocalDate.of(1901, 1, 1), nota.getDataInicial());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() == null && nota.getUsuarioInsercao() != null && nota.getDataInicial() != null && nota.getDataFinal() == null) {
			List<NotaFiscalModel> list = repository.buscarNota(" ", nota.getUsuarioInsercao(), nota.getDataInicial(), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() != null && nota.getUsuarioInsercao() == null && nota.getDataInicial() != null && nota.getDataFinal() != null) {
			List<NotaFiscalModel> list = repository.buscarNota(nota.getNotaFiscal(), " ", nota.getDataInicial(), nota.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() != null && nota.getUsuarioInsercao() == null && nota.getDataInicial() == null && nota.getDataFinal() != null) {
			List<NotaFiscalModel> list = repository.buscarNota(nota.getNotaFiscal(),  " ", LocalDate.of(1901, 1, 1), nota.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() != null && nota.getUsuarioInsercao() == null && nota.getDataInicial() != null && nota.getDataFinal() == null) {
			List<NotaFiscalModel> list = repository.buscarNota(nota.getNotaFiscal(), nota.getUsuarioInsercao(), nota.getDataInicial(), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() != null && nota.getUsuarioInsercao() != null && nota.getDataInicial() == null && nota.getDataFinal() != null) {
			List<NotaFiscalModel> list = repository.buscarNota(nota.getNotaFiscal(), nota.getUsuarioInsercao(), LocalDate.of(1901, 1, 1), nota.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() != null && nota.getUsuarioInsercao() != null && nota.getDataInicial() != null && nota.getDataFinal() == null) {
			List<NotaFiscalModel> list = repository.buscarNota(nota.getNotaFiscal(), nota.getUsuarioInsercao(), nota.getDataInicial(), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() != null && nota.getUsuarioInsercao() != null && nota.getDataInicial() == null && nota.getDataFinal() == null) {
			List<NotaFiscalModel> list = repository.buscarNota(nota.getNotaFiscal(), nota.getUsuarioInsercao(), LocalDate.of(1901, 1, 1), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() == null && nota.getUsuarioInsercao() == null && nota.getDataInicial() != null && nota.getDataFinal() != null) {
			List<NotaFiscalModel> list = repository.buscarNotaPorData(nota.getDataInicial(), nota.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() == null && nota.getUsuarioInsercao() == null && nota.getDataInicial() == null && nota.getDataFinal() != null) {
			List<NotaFiscalModel> list = repository.buscarNotaPorData(LocalDate.of(1901, 1, 1), nota.getDataFinal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() == null && nota.getUsuarioInsercao() == null && nota.getDataInicial() != null && nota.getDataFinal() == null) {
			List<NotaFiscalModel> list = repository.buscarNotaPorData(nota.getDataInicial(), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() != null && nota.getUsuarioInsercao() == null && nota.getDataInicial() == null && nota.getDataFinal() == null) {
			List<NotaFiscalModel> list = repository.buscarNotaPorValor(nota.getNotaFiscal());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() == null && nota.getUsuarioInsercao() != null && nota.getDataInicial() == null && nota.getDataFinal() == null) {
			List<NotaFiscalModel> list = repository.buscarNotaPorNomeUsuario(nota.getUsuarioInsercao());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		if(nota.getNotaFiscal() == null && nota.getUsuarioInsercao() == null && nota.getDataInicial() == null && nota.getDataFinal() == null) {
			List<NotaFiscalModel> list = repository.buscarNotaPorData(LocalDate.of(1901, 1, 1), LocalDate.now());
			list.forEach(item -> listDTO.add(SimpleMapper.INSTANCE.nota2NotaDTO(item)));
		}
		
		return listDTO;
	}
}
