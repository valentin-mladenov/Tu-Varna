package com.vale.warehouses.app.repository;

import com.vale.warehouses.app.model.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeasingContractRepository extends JpaRepository<LeasingContract, Long> {
    List<LeasingContract> findBySaleAgentId(Long id);

    List<LeasingContract> findByOwnerId(Long id);

}