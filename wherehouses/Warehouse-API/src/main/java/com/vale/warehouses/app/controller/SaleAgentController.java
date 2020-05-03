package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.SaleAgent;
import com.vale.warehouses.app.service.interfaces.SaleAgentInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/saleAgent")
public class SaleAgentController {
    @Autowired
    private SaleAgentInterface service;

    private static final Logger logger = LogManager.getLogger(SaleAgentController.class);

    /*---get all saleAgents---*/
    @GetMapping
    public ResponseEntity<List<SaleAgent>> list() {
        try {
            List<SaleAgent> saleAgents = service.getSaleAgents();

            for (SaleAgent saleAgent : saleAgents) {
                saleAgent.setWarehouses(new HashSet<>());
            }

            return ResponseEntity.ok(saleAgents);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Get a saleAgent by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<SaleAgent> get(@PathVariable("id") long id) {
        try {
            SaleAgent saleAgent = service.getSaleAgent(id);

            return ResponseEntity.ok(saleAgent);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Update a saleAgent by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<SaleAgent> update(
            @PathVariable("id") long id,
            @RequestBody SaleAgent saleAgent) {
        try {
            service.updateSaleAgent(saleAgent);

            return ResponseEntity.ok(saleAgent);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Delete a saleAgent by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            service.deleteSaleAgent(id);

            return (ResponseEntity<?>) ResponseEntity.noContent();
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }
}