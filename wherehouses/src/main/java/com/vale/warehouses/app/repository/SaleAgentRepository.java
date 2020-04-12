package com.vale.warehouses.app.repository;

import com.vale.warehouses.app.model.SaleAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleAgentRepository extends JpaRepository<SaleAgent, Long> {

}