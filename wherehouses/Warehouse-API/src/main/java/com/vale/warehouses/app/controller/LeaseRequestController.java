package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.LeaseRequest;
import com.vale.warehouses.app.service.interfaces.LeaseRequestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaseRequest")
public class LeaseRequestController {
    @Autowired
    private LeaseRequestInterface service;

    /*---get all leaseRequests---*/
    @GetMapping
    public ResponseEntity<List<LeaseRequest>> list() {
        List<LeaseRequest> leaseRequests = service.getLeaseRequests();

        return ResponseEntity.ok(leaseRequests);
    }

    /*---Get a leaseRequest by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<LeaseRequest> get(@PathVariable("id") long id) {
        LeaseRequest leaseRequest = service.getLeaseRequest(id);

        return ResponseEntity.ok(leaseRequest);
    }

    /*---Add new leaseRequest---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody LeaseRequest leaseRequest) {
        leaseRequest = service.createLeaseRequest(leaseRequest);

        return ResponseEntity.ok(leaseRequest);
    }

    /*---Update a leaseRequest by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<LeaseRequest> update(@PathVariable("id") long id, @RequestBody LeaseRequest leaseRequest) {
        service.updateLeaseRequest(leaseRequest);

        return  ResponseEntity.ok(leaseRequest);
    }

    /*---Delete a leaseRequest by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.deleteLeaseRequest(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}