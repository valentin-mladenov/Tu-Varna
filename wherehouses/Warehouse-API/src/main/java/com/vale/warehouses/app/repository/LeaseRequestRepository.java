package com.vale.warehouses.app.repository;

import com.vale.warehouses.app.model.LeaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaseRequestRepository extends JpaRepository<LeaseRequest, Long> {
    List<LeaseRequest> findByTenantIdAndLeasingContractIsNull(Long id);

    List<LeaseRequest> findByLeasingContractIsNull();
}