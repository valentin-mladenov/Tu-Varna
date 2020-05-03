package com.vale.warehouses.app.repository;

import com.vale.warehouses.app.model.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface LeasingContractRepository extends JpaRepository<LeasingContract, Long> {
    List<LeasingContract> findBySaleAgentId(Long id);

    List<LeasingContract> findByWarehouseIdInOrderByLeasedAtDesc(Set<Long> ids);

    List<LeasingContract> findByWarehouseIdInAndLeasedTillAfterAndLeasedAtBeforeOrderByLeasedAtDesc(
            Set<Long> ids, OffsetDateTime fromDate, OffsetDateTime toDate);

    List<LeasingContract> findBySaleAgentIdAndLeasedAtBetween(Long id, OffsetDateTime fromDate, OffsetDateTime toDate);

    List<LeasingContract> findBySaleAgentIdAndLeasedTillBetweenOrderByLeasedTillDesc(Long id, OffsetDateTime fromDate, OffsetDateTime toDate);

    List<LeasingContract> findByOwnerIdAndLeasedTillBetweenOrderByLeasedTillDesc(Long id, OffsetDateTime fromDate, OffsetDateTime toDate);

    List<LeasingContract> findBySaleAgentIdAndLeasedTillAfter(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> findByOwnerIdAndLeasedTillAfter(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> findByOwnerId(Long id);

}