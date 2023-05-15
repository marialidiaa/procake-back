package com.procake.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procake.v1.models.GrupoAcessoModel;

public interface GrupoAcessoRepositories extends JpaRepository<GrupoAcessoModel, UUID> {

}