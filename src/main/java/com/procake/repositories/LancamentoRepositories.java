package com.procake.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.procake.v1.models.LancamentoModel;

public interface LancamentoRepositories extends JpaRepository<LancamentoModel, UUID> {
	
	@Query("SELECT L FROM LancamentoModel L WHERE L.notaFiscal.id = :id")
	List<LancamentoModel> buscarPorNotaFiscalId(UUID id);

	@Query("SELECT L FROM LancamentoModel L WHERE L.insumo.id = :id AND L.quantidade > 0")
	List<LancamentoModel> buscarPorInsumoQuantidadeMaior0(UUID id); 
	
	@Query("SELECT L FROM LancamentoModel L WHERE L.insumo.id = :id")
	List<LancamentoModel> buscarPorInsumoID(UUID id); 

}
