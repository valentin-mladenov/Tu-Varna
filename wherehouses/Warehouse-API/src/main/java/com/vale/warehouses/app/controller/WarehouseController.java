package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.Warehouse;
import com.vale.warehouses.app.service.interfaces.WarehouseInterface;
import com.vale.warehouses.auth.models.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseInterface service;

    /*---get all warehouses---*/
    @GetMapping
    public ResponseEntity<List<Warehouse>> list() {
        throwExceptionIfAccessForbidden(RoleType.Admin);

        List<Warehouse> warehouses = service.getWarehouses();

        for (Warehouse warehouse: warehouses) {
            warehouse.setSaleAgents(new HashSet<>());
            warehouse.getOwner().setWarehouses(null);
        }

        return ResponseEntity.ok().body(warehouses);
    }

    /*---get all warehouses---*/
    @GetMapping("forOwner/{id}")
    public ResponseEntity<List<Warehouse>> listForOwner(@PathVariable("id") long id) {
        throwExceptionIfAccessForbidden(RoleType.Owner);

        List<Warehouse> warehouses = service.getWarehousesForOwner(id);

        for (Warehouse warehouse: warehouses) {
            warehouse.setSaleAgents(new HashSet<>());
            warehouse.getOwner().setWarehouses(null);
        }

        return ResponseEntity.ok().body(warehouses);
    }

    /*---get all warehouses---*/
    @GetMapping("forSaleAgent/{id}")
    public ResponseEntity<List<Warehouse>> listForSaleAgent(@PathVariable("id") long id) {
        throwExceptionIfAccessForbidden(RoleType.Agent);

        List<Warehouse> warehouses = service.getWarehousesForSaleAgent(id);

        for (Warehouse warehouse: warehouses) {
            warehouse.setSaleAgents(new HashSet<>());
            warehouse.getOwner().setWarehouses(null);
        }

        return ResponseEntity.ok().body(warehouses);
    }

    /*---Get a warehouse by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> get(@PathVariable("id") long id) {
        Warehouse warehouse = service.getWarehouse(id);

        warehouse.getSaleAgents().forEach(saleAgent -> saleAgent.setWarehouses(new HashSet<>()));

        return ResponseEntity.ok(warehouse);
    }

    /*---Add new warehouse---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Warehouse warehouse) {
        throwExceptionIfAccessForbidden(RoleType.Owner);

        warehouse = service.createWarehouse(warehouse);

        warehouse.getSaleAgents().forEach(saleAgent -> saleAgent.setWarehouses(new HashSet<>()));

        return ResponseEntity.ok(warehouse);
    }

    /*---Update a warehouse by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> update(
            @PathVariable("id") long id,
            @RequestBody Warehouse warehouse
    ) {
        throwExceptionIfAccessForbidden(RoleType.Owner);

        service.updateWarehouse(warehouse);

        return  ResponseEntity.ok(warehouse);
    }

    /*---Delete a warehouse by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        throwExceptionIfAccessForbidden(RoleType.Owner);

        service.deleteWarehouse(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }

    private void throwExceptionIfAccessForbidden(RoleType type) throws AccessDeniedException {
        boolean isAllowed = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals(type.toString()));

        if (!isAllowed) {
            throw new AccessDeniedException("Admins only");
        }
    }
}