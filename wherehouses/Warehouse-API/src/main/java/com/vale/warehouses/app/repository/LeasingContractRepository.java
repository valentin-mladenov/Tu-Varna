package com.vale.warehouses.app.repository;

import com.vale.warehouses.app.model.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface LeasingContractRepository extends JpaRepository<LeasingContract, Long> {
    List<LeasingContract> findBySaleAgentId(Long id);

    List<LeasingContract> findByWarehouseIdOrderByLeasedAtDesc(Long id);

    List<LeasingContract> findBySaleAgentIdAndLeasedAtBetween(Long id, OffsetDateTime fromDate, OffsetDateTime toDate);

    List<LeasingContract> findBySaleAgentIdAndLeasedTillBeforeOrderByLeasedTillDesc(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> findByOwnerIdAndLeasedTillBeforeOrderByLeasedTillDesc(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> findBySaleAgentIdAndLeasedTillAfter(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> findByOwnerIdAndLeasedTillAfter(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> findByOwnerId(Long id);

}