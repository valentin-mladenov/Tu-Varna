package com.vale.warehouses.app.repository;

import com.vale.warehouses.app.model.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeasingContractRepository extends JpaRepository<LeasingContract, Long> {

}