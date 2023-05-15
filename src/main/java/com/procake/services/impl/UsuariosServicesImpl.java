package com.procake.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.procake.exceptions.DadosInvalidosException;
import com.procake.exceptions.RecursoNaoEncontradoException;
import com.procake.mappers.SimpleMapper;
import com.procake.repositories.UsuarioRepositories;
import com.procake.services.IGrupoAcessoServices;
import com.procake.services.IUsuariosServices;
import com.procake.utils.GeradorSenha;
import com.procake.utils.ToUpper;
import com.procake.utils.TokenUtils;
import com.procake.v1.dtos.UsuarioDTO;
import com.procake.v1.models.UsuarioModel;

@Component
public class UsuariosServicesImpl implements IUsuariosServices {

	Logger logger = LoggerFactory.getLogger(UsuariosServicesImpl.class);

	private UsuarioRepositories usuarioRepository;
	private PasswordEncoder passwordEncoder;
	private IGrupoAcessoServices grupoAcessoService;

	public UsuariosServicesImpl(UsuarioRepositories usuarioRepository, PasswordEncoder passwordEncoder,
			IGrupoAcessoServices grupoAcessoService) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		this.grupoAcessoService = grupoAcessoService;
	}

	@Override
	public Page<UsuarioDTO> listarTodos(Pageable pageable) {
		Page<UsuarioModel> page = usuarioRepository.findAll(pageable);
		return page.map(u -> SimpleMapper.INSTANCE.usuario2UsuarioDTO(u));
	}
	
	@Override
	public Page<UsuarioDTO> listarTodosAtivos(Pageable pageable) {
		Page<UsuarioModel> page = usuarioRepository.listarTodosAtivos(pageable);
		return page.map(u -> SimpleMapper.INSTANCE.usuario2UsuarioDTO(u));
	}
	
	@Override
	public Page<UsuarioDTO> listarTodosDesativados(Pageable pageable) {
		Page<UsuarioModel> page = usuarioRepository.listarTodosDesativados(pageable);
		return page.map(u -> SimpleMapper.INSTANCE.usuario2UsuarioDTO(u));
	}

	@Override
	public UsuarioDTO buscarPorId(UUID id) {
		return usuarioRepository.findById(id).map(u -> SimpleMapper.INSTANCE.usuario2UsuarioDTO(u))
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com esse id: " + id));
	}
	
	@Override
	public List<UsuarioDTO> buscarPorNome(String name) {
		
		if(name.equals("undefined")) {
			name = " ";
		}

		List<UsuarioModel> listUser = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
		List<UsuarioDTO> list = new ArrayList<>();	
		 listUser.forEach(item -> list.add(SimpleMapper.INSTANCE.usuario2UsuarioDTO(item)));
		return list;
	}


	@Override
	public UsuarioDTO inserir(UsuarioDTO insertUser) {
		
		String senha = GeradorSenha.getSenha();
		insertUser.setGrupoAcesso(grupoAcessoService.buscarPorID(insertUser.getGrupoAcesso().getId()));
		UsuarioModel user = new UsuarioModel(insertUser.getNome(), insertUser.getUsername(), 
				passwordEncoder.encode(senha),insertUser.getCpfCnpj(), insertUser.getTelefone(), true,
				SimpleMapper.INSTANCE.grupoAcessoDTO2GrupoAcesso(insertUser.getGrupoAcesso())); 
				
		ToUpper.UPPER_USUARIO_MODEL(user);
		user = usuarioRepository.save(user);

		return SimpleMapper.INSTANCE.usuario2UsuarioDTO(user);
	}

	@Override
	public UsuarioDTO atualizar(UUID id, UsuarioDTO usuario) {
		usuario.setGrupoAcesso(grupoAcessoService.buscarPorID(usuario.getGrupoAcesso().getId()));
		
		UsuarioModel model = usuarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com esse id: " + id));

		model.setNome(usuario.getNome());
		model.setUsername(usuario.getUsername());
		model.setTelefone(usuario.getTelefone());
		model.setGrupoAcesso(SimpleMapper.INSTANCE.grupoAcessoDTO2GrupoAcesso(usuario.getGrupoAcesso()));
		model.setEnabled(usuario.getEnabled());
		ToUpper.UPPER_USUARIO_MODEL(model);
		model = usuarioRepository.saveAndFlush(model);

		return SimpleMapper.INSTANCE.usuario2UsuarioDTO(model);
	}

	@Override
	public UsuarioDTO alterarSenha(UUID id, String senha) {

		UsuarioModel userDB = usuarioRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com esse id: " + id));

		String usernameDB = userDB.getUsername().toUpperCase().trim();
		String usernameToken = TokenUtils.buscarUsuario().toUpperCase();
		if (!usernameDB.equals(usernameToken)) {
			throw new DadosInvalidosException("ID inválido, favor verificar os dados informados e tentar novamente");
		}
		userDB.setPassword(passwordEncoder.encode(senha));
		userDB = usuarioRepository.save(userDB);
		return SimpleMapper.INSTANCE.usuario2UsuarioDTO(userDB);
	}
}