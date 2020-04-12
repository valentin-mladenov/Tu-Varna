package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.service.interfaces.LeasingContractInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leasingContract")
public class LeasingContractController {
    @Autowired
    private LeasingContractInterface service;

    /*---get all LeasingContracts---*/
    @GetMapping
    public ResponseEntity<List<LeasingContract>> list() {
        List<LeasingContract> LeasingContracts = service.getLeasingContracts();

        return ResponseEntity.ok(LeasingContracts);
    }

    /*---Get a LeasingContract by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<LeasingContract> get(@PathVariable("id") long id) {
        LeasingContract leasingContract = service.getLeasingContract(id);

        return ResponseEntity.ok(leasingContract);
    }

    /*---Add new LeasingContract---*/
    @PostMapping
    public ResponseEntity<LeasingContract> save(@RequestBody LeasingContract leasingContract) {
        leasingContract = service.createLeasingContract(leasingContract);

        return ResponseEntity.ok(leasingContract);
    }

    /*---Update a LeasingContract by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<LeasingContract> update(
            @PathVariable("id") long id, @RequestBody LeasingContract leasingContract
    ) {
        leasingContract = service.updateLeasingContract(leasingContract);

        return  ResponseEntity.ok(leasingContract);
    }

    /*---Delete a LeasingContract by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.deleteLeasingContract(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}