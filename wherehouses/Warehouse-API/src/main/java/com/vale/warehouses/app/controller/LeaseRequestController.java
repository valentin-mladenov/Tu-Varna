package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.LeaseRequest;
import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.service.interfaces.LeaseRequestInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaseRequest")
public class LeaseRequestController {
    @Autowired
    private LeaseRequestInterface service;

    private static final Logger logger = LogManager.getLogger(LeaseRequestController.class);

    /*---get all leaseRequests---*/
    @GetMapping
    public ResponseEntity<List<LeaseRequest>> list() {
        try {
            List<LeaseRequest> leaseRequests = service.getLeaseRequests();

            for (LeaseRequest leaseRequest : leaseRequests) {
                nullifyContractData(leaseRequest);
            }

            return ResponseEntity.ok(leaseRequests);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---get all not completed leaseRequests---*/
    @GetMapping("/notCompeted/{id}")
    public ResponseEntity<List<LeaseRequest>> listNotCompeted(@PathVariable("id") long id) {
        try {
            List<LeaseRequest> leaseRequests = service.getLeaseRequestsWithoutContract(id);

            return ResponseEntity.ok(leaseRequests);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---get all not completed leaseRequests---*/
    @GetMapping("/notCompeted")
    public ResponseEntity<List<LeaseRequest>> listNotCompeted() {
        try {
            List<LeaseRequest> leaseRequests = service.getLeaseRequestsWithoutContract(null);

            return ResponseEntity.ok(leaseRequests);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Get a leaseRequest by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<LeaseRequest> get(@PathVariable("id") long id) {
        try {
            LeaseRequest leaseRequest = service.getLeaseRequest(id);

            nullifyContractData(leaseRequest);

            return ResponseEntity.ok(leaseRequest);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Add new leaseRequest---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody LeaseRequest leaseRequest) {
        try {
            leaseRequest = service.createLeaseRequest(leaseRequest);

            return ResponseEntity.ok(leaseRequest);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Update a leaseRequest by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<LeaseRequest> update(
            @PathVariable("id") long id,
            @RequestBody LeaseRequest leaseRequest) {
        try {
            service.updateLeaseRequest(leaseRequest);

            return ResponseEntity.ok(leaseRequest);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Delete a leaseRequest by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            service.deleteLeaseRequest(id);

            return (ResponseEntity<?>) ResponseEntity.noContent();
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    private void nullifyContractData(LeaseRequest leaseRequest) {
        LeasingContract leasingContract = leaseRequest.getLeasingContract();
        if (leasingContract != null) {
            leasingContract.setLeaseRequest(null);
            leasingContract.getSaleAgent().setWarehouses(null);
            leasingContract.getWarehouse().setSaleAgents(null);
            leasingContract.getWarehouse().setOwner(null);
            leasingContract.setTenant(null);
        }
    }
}