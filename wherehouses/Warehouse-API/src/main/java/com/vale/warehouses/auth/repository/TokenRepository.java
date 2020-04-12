package com.vale.warehouses.auth.repository;

import com.vale.warehouses.auth.models.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {

}
