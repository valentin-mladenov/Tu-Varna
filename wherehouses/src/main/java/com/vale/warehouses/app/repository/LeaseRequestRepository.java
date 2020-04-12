package com.vale.warehouses.app.repository;

import com.vale.warehouses.app.model.LeaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaseRequestRepository extends JpaRepository<LeaseRequest, Long> {

}