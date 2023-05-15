package com.procake.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.procake.v1.models.RoleModel;

public interface RolesRepositories extends JpaRepository<RoleModel, UUID> {

}