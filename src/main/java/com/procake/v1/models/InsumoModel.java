package com.procake.v1.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.procake.v1.models.enums.UnidadeMedida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_insumos")
public class InsumoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	@Generated(GenerationTime.INSERT)
	@Column(name = "codigo", columnDefinition = "serial", updatable = false)
	private BigInteger codigo;
	@Column(name = "nome", nullable = false, unique = true)
	private String nome;
	@Column(name = "unidade_medida", nullable = false, unique = false)
	private UnidadeMedida unidadeMedida;
	@Column(name = "descricao", nullable = false, unique = false)
	private String descricao;
	@Column(name = "enabled", nullable = false, unique = false)
	private boolean enabled;

	@JsonIgnore
	@OneToMany(mappedBy = "insumo", fetch = FetchType.EAGER)
	private List<EstoqueModel> estoque = new ArrayList<>();

	public InsumoModel() {

	}

	public InsumoModel(UUID id, BigInteger codigo, String nome, UnidadeMedida unidadeMedida, String descricao,
			boolean enabled) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.unidadeMedida = unidadeMedida;
		this.descricao = descricao;
		this.enabled = enabled;
	}

	public List<EstoqueModel> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<EstoqueModel> estoque) {
		this.estoque = estoque;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public BigInteger getCodigo() {
		return codigo;
	}

	public void setCodigo(BigInteger codigo) {
		this.codigo = codigo;
	}

}