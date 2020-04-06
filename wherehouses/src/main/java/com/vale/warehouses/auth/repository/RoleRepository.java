package com.vale.warehouses.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vale.warehouses.auth.models.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
