package com.vale.warehouses.app.repository;

import com.vale.warehouses.app.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findBySaleAgentsId(Long id);

    List<Warehouse> findByOwnerId(Long id);
    
}