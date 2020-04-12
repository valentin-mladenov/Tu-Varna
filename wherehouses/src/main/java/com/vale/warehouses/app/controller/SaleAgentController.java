package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.SaleAgent;
import com.vale.warehouses.app.service.interfaces.SaleAgentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saleAgent")
public class SaleAgentController {
    @Autowired
    private SaleAgentInterface service;

    /*---get all saleAgents---*/
    @GetMapping
    public ResponseEntity<List<SaleAgent>> list() {
        List<SaleAgent> saleAgents = service.getSaleAgents();

        return ResponseEntity.ok(saleAgents);
    }

    /*---Get a saleAgent by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<SaleAgent> get(@PathVariable("id") long id) {
        SaleAgent saleAgent = service.getSaleAgent(id);

        return ResponseEntity.ok(saleAgent);
    }

    /*---Add new saleAgent---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody SaleAgent saleAgent) {
        saleAgent = service.createSaleAgent(saleAgent);

        return ResponseEntity.ok(saleAgent);
    }

    /*---Update a saleAgent by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<SaleAgent> update(@PathVariable("id") long id, @RequestBody SaleAgent saleAgent) {
        service.updateSaleAgent(saleAgent);

        return  ResponseEntity.ok(saleAgent);
    }

    /*---Delete a saleAgent by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.deleteSaleAgent(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}