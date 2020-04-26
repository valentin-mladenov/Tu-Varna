package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.LeaseRequest;
import com.vale.warehouses.app.model.LeasingContract;
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

        for (LeaseRequest leaseRequest: leaseRequests) {
            nullifyContractData(leaseRequest);
        }

        return ResponseEntity.ok(leaseRequests);
    }

    /*---get all not completed leaseRequests---*/
    @GetMapping("/notCompeted/{id}")
    public ResponseEntity<List<LeaseRequest>> listNotCompeted(@PathVariable("id") long id) {
        List<LeaseRequest> leaseRequests = service.getLeaseRequestsWithoutContract(id);

        return ResponseEntity.ok(leaseRequests);
    }

    /*---Get a leaseRequest by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<LeaseRequest> get(@PathVariable("id") long id) {
        LeaseRequest leaseRequest = service.getLeaseRequest(id);

        nullifyContractData(leaseRequest);

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

    private void nullifyContractData(LeaseRequest leaseRequest) {
        LeasingContract leasingContract =  leaseRequest.getLeasingContract();
        if(leasingContract != null){
            leasingContract.setLeaseRequest(null);
            leasingContract.getSaleAgent().setWarehouses(null);
            leasingContract.getWarehouse().setSaleAgents(null);
            leasingContract.getWarehouse().setOwner(null);
            leasingContract.setTenant(null);
        }
    }
}