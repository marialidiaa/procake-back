package com.procake.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.procake.v1.dtos.ClienteDTO;
import com.procake.v1.dtos.FornecedoresDTO;
import com.procake.v1.dtos.GrupoAcessoDTO;
import com.procake.v1.dtos.InsumoDTO;
import com.procake.v1.dtos.LancamentoDTO;
import com.procake.v1.dtos.MarcaDTO;
import com.procake.v1.dtos.NotaFiscalDTO;
import com.procake.v1.dtos.RoleDTO;
import com.procake.v1.dtos.UsuarioDTO;
import com.procake.v1.models.ClienteModel;
import com.procake.v1.models.FornecedoresModel;
import com.procake.v1.models.GrupoAcessoModel;
import com.procake.v1.models.InsumoModel;
import com.procake.v1.models.LancamentoModel;
import com.procake.v1.models.MarcaModel;
import com.procake.v1.models.NotaFiscalModel;
import com.procake.v1.models.RoleModel;
import com.procake.v1.models.UsuarioModel;

@Mapper
public interface SimpleMapper {

	SimpleMapper INSTANCE = Mappers.getMapper(SimpleMapper.class);

	GrupoAcessoDTO grupoAcesso2GrupoAcessoDTO(GrupoAcessoModel group);

	GrupoAcessoModel grupoAcessoDTO2GrupoAcesso(GrupoAcessoDTO group);

	RoleDTO role2RoleDTO(RoleModel role);

	RoleModel roleDTO2Role(RoleDTO role);

	UsuarioDTO usuario2UsuarioDTO(UsuarioModel user);

	UsuarioModel usuarioDTO2Usuario(UsuarioDTO user);

	InsumoDTO insumo2InsumoDTO(InsumoModel insumo);

	InsumoModel insumoDTO2Insumo(InsumoDTO insumo);
	
	MarcaDTO marca2MarcaDTO(MarcaModel marca);

	MarcaModel marcaDTO2Marca(MarcaDTO marca);
	
	FornecedoresDTO fornecedores2FornecedoresDTO(FornecedoresModel forenecedores);
	
	FornecedoresModel fornecedoresDTO2Fornecedores(FornecedoresDTO fornecedores);
	
	ClienteDTO cliente2ClienteDTO(ClienteModel cliente);
	
	ClienteModel clienteDTO2cliente(ClienteDTO cliente);
	
	NotaFiscalDTO nota2NotaDTO(NotaFiscalModel nota);
	
	NotaFiscalModel notaDTO2Nota(NotaFiscalDTO nota);
	
	LancamentoDTO lancamento2LancamentoDTO (LancamentoModel lancamento);
	
	LancamentoModel lancamentoDTO2Lancamento (LancamentoDTO lancamento);
}
