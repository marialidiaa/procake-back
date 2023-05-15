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
import com.procake.repositories.GrupoAcessoRepositories;
import com.procake.repositories.UsuarioRepositories;
import com.procake.services.IGrupoAcessoServices;
import com.procake.v1.dtos.GrupoAcessoDTO;
import com.procake.v1.models.GrupoAcessoModel;
import com.procake.v1.models.RoleModel;
import com.procake.v1.models.UsuarioModel;

import jakarta.transaction.Transactional;

@Component
public class GrupoAcessoServicesImpl implements IGrupoAcessoServices {

	private GrupoAcessoRepositories grupoAcessoResository;
	private UsuarioRepositories usuarioRepository;

	public GrupoAcessoServicesImpl(GrupoAcessoRepositories grupoAcessoResository, UsuarioRepositories usuarioRepository) {
		this.grupoAcessoResository = grupoAcessoResository;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Page<GrupoAcessoDTO> listarTodos(Pageable pageable) {
		Page<GrupoAcessoModel> page = grupoAcessoResository.findAll(pageable);
		return page.map(r -> SimpleMapper.INSTANCE.grupoAcesso2GrupoAcessoDTO(r));
	}

	@Override
	public GrupoAcessoDTO buscarPorID(UUID id) {
		return grupoAcessoResository.findById(id).map(g -> SimpleMapper.INSTANCE.grupoAcesso2GrupoAcessoDTO(g))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Grupo de acesso não encontrado por esse ID: " + id));
	}

	@Transactional
	@Override
	public GrupoAcessoDTO inserir(GrupoAcessoDTO accessGroupDTO) {

		GrupoAcessoModel model = SimpleMapper.INSTANCE.grupoAcessoDTO2GrupoAcesso(accessGroupDTO);
		model = toUpperCase(model);
		model = grupoAcessoResository.save(model);

		return SimpleMapper.INSTANCE.grupoAcesso2GrupoAcessoDTO(model);
	}

	@Transactional
	@Override
	public GrupoAcessoDTO atualizar(UUID id, GrupoAcessoDTO grupoAcessoDTO) {

		GrupoAcessoModel model = grupoAcessoResository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Grupo de acesso não encontrado por esse ID: " + id));
		model.getRoles().clear();
		grupoAcessoResository.save(model);
		model.setNome(grupoAcessoDTO.getNome());
		model = SimpleMapper.INSTANCE.grupoAcessoDTO2GrupoAcesso(grupoAcessoDTO);
		model.setId(id);
		model = toUpperCase(model);

		if (!grupoAcessoDTO.getRoles().isEmpty()) {
			List<RoleModel> list = new ArrayList<>();
			grupoAcessoDTO.getRoles().forEach((r) -> list.add(SimpleMapper.INSTANCE.roleDTO2Role(r)));
			model.setRoles(list);
		}

		model = grupoAcessoResository.save(model);
		return SimpleMapper.INSTANCE.grupoAcesso2GrupoAcessoDTO(model);
	}

	@Transactional
	@Override
	public String deletar(UUID id) {

		GrupoAcessoModel accessGroupDB = grupoAcessoResository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Grupo de acesso não encontrado por esse ID: " + id));
		List<UsuarioModel> list = usuarioRepository.findByGrupoAcesso(accessGroupDB);
		if (list.size() > 0) {
			throw new OperacaoInvalidaException(
					"Operação inválida, o grupo de acesso tem pelo menos uma pessoa associada. Remova e tente novamente");
		}
		grupoAcessoResository.deleteById(id);

		return "Grupo de acesso deletado com sucesso";
	}

	private GrupoAcessoModel toUpperCase(GrupoAcessoModel model) {
		model.setNome(model.getNome().trim().toUpperCase());
		return model;
	}

}