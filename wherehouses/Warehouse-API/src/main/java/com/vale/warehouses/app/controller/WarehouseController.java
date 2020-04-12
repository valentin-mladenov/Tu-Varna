package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.Warehouse;
import com.vale.warehouses.app.service.interfaces.WarehouseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseInterface service;

    /*---get all warehouses---*/
    @GetMapping
    public ResponseEntity<List<Warehouse>> list() {
        List<Warehouse> warehouses = service.getWarehouses();

        return ResponseEntity.ok(warehouses);
    }

    /*---Get a warehouse by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> get(@PathVariable("id") long id) {
        Warehouse warehouse = service.getWarehouse(id);

        return ResponseEntity.ok(warehouse);
    }

    /*---Add new warehouse---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Warehouse warehouse) {
        warehouse = service.createWarehouse(warehouse);

        return ResponseEntity.ok(warehouse);
    }

    /*---Update a warehouse by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> update(@PathVariable("id") long id, @RequestBody Warehouse warehouse) {
        service.updateWarehouse(warehouse);

        return  ResponseEntity.ok(warehouse);
    }

    /*---Delete a warehouse by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.deleteWarehouse(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}