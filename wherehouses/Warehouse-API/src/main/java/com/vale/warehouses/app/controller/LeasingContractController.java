package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.service.interfaces.LeasingContractInterface;
import com.vale.warehouses.auth.models.RoleType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/leasingContract")
public class LeasingContractController {
    private static final Logger logger = LogManager.getLogger(LeasingContractController.class);

    @Autowired
    private LeasingContractInterface service;

    /*---get all LeasingContracts---*/
    @GetMapping
    public ResponseEntity<List<LeasingContract>> list() {
       throwExceptionIfAccessForbidden(RoleType.Admin);

        List<LeasingContract> leasingContracts = service.getLeasingContracts();

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok(leasingContracts);
    }

    /*---get all warehouses---*/
    @GetMapping("warehouse/{id}")
    public ResponseEntity<List<LeasingContract>> allForWarehouse(
            @PathVariable("id") long id
    ) {
        throwExceptionIfAccessForbidden(RoleType.Agent);

        List<LeasingContract> leasingContracts = service
            .getLeasingContractsForWarehouse(
                new HashSet<>(Collections.singletonList(id)), null, null);

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok(leasingContracts);
    }

    /*---get all warehouses---*/
    @GetMapping("forOwner/{id}")
    public ResponseEntity<List<LeasingContract>> listForOwner(@PathVariable("id") long id) {
        throwExceptionIfAccessForbidden(RoleType.Owner);

        List<LeasingContract> leasingContracts = service.getLeasingContractsForOwner(id);

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok(leasingContracts);
    }

    /*---get all warehouses---*/
    @GetMapping("forSaleAgent/{id}")
    public ResponseEntity<List<LeasingContract>> listForSaleAgent(
            @PathVariable("id") long id,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) OffsetDateTime fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) OffsetDateTime toDate
    ) {
        if (fromDate == null) {
            fromDate = OffsetDateTime.now();
            fromDate = fromDate.minusYears(100);
        }

        if (toDate == null) {
            toDate = fromDate.plusYears(200);
        }

        throwExceptionIfAccessForbidden(RoleType.Agent);

        List<LeasingContract> leasingContracts = service.
                getLeasingContractsForSaleAgent(id, fromDate, toDate);

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok(leasingContracts);
    }

    /*---get all warehouses---*/
    @GetMapping("currentlyLeased/forOwner/{id}")
    public ResponseEntity<List<LeasingContract>> listCurrentlyLeasedForOwner(
            @PathVariable("id") long id
    ) {
        throwExceptionIfAccessForbidden(RoleType.Owner);

        List<LeasingContract> leasingContracts = service
                .getCurrentlyActiveLeasingContractsForOwner(id, OffsetDateTime.now());

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok().body(leasingContracts);
    }

    /*---get all warehouses---*/
    @GetMapping("currentlyLeased/forSaleAgent/{id}")
    public ResponseEntity<List<LeasingContract>> listCurrentlyLeasedForSaleAgent(
            @PathVariable("id") long id
    ) {
        throwExceptionIfAccessForbidden(RoleType.Agent);

        List<LeasingContract> leasingContracts = service
                .getCurrentlyActiveLeasingContractsForSaleAgent(id, OffsetDateTime.now());

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok().body(leasingContracts);
    }

    /*---get all warehouses---*/
    @GetMapping("endingSoon/forSaleAgent/{id}")
    public ResponseEntity<List<LeasingContract>> endingSoonForSaleAgent(@PathVariable("id") long id) {
        throwExceptionIfAccessForbidden(RoleType.Agent);

        OffsetDateTime monthFromNow = OffsetDateTime.now().plusMonths(1).plusDays(3);

        List<LeasingContract> leasingContracts = service
                .getEndingSoonLeasingContractsForSaleAgent(id, monthFromNow);

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok(leasingContracts);
    }

    /*---get all warehouses---*/
    @GetMapping("endingSoon/forOwner/{id}")
    public ResponseEntity<List<LeasingContract>> endingSoonForOwner(
            @PathVariable("id") long id
    ) {
        throwExceptionIfAccessForbidden(RoleType.Owner);

        OffsetDateTime monthFromNow = OffsetDateTime.now().plusMonths(1).plusDays(3);

        List<LeasingContract> leasingContracts = service
                .getEndingSoonLeasingContractsForOwner(id, monthFromNow);

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok(leasingContracts);
    }

    /*---Get a LeasingContract by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<LeasingContract> get(@PathVariable("id") long id) {
        LeasingContract leasingContract = service.getLeasingContract(id);

        nullifyNestedObjects(leasingContract);

        return ResponseEntity.ok(leasingContract);
    }

    /*---Add new LeasingContract---*/
    @PostMapping
    public ResponseEntity<LeasingContract> save(@RequestBody LeasingContract leasingContract) {
        leasingContract = service.createLeasingContract(leasingContract);

        nullifyNestedObjects(leasingContract);

        return ResponseEntity.ok(leasingContract);
    }

    /*---Update a LeasingContract by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<LeasingContract> update(
            @PathVariable("id") long id, @RequestBody LeasingContract leasingContract
    ) {
        leasingContract = service.updateLeasingContract(leasingContract);

        nullifyNestedObjects(leasingContract);

        return  ResponseEntity.ok(leasingContract);
    }

    /*---Delete a LeasingContract by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.deleteLeasingContract(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }

    private void throwExceptionIfAccessForbidden(RoleType type) throws AccessDeniedException {
        boolean isAllowed = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals(type.toString())
                        || r.getAuthority().equals(RoleType.Admin.toString()));

        if (!isAllowed) {
            throw new AccessDeniedException("Only accessable for: " + type.toString());
        }
    }

    private void nullifyNestedObjects(LeasingContract leasingContract) {
        leasingContract.getWarehouse().setSaleAgents(new HashSet<>());
        leasingContract.getWarehouse().setOwner(null);
        leasingContract.getSaleAgent().setWarehouses(new HashSet<>());

        if (leasingContract.getLeaseRequest() != null) {
            leasingContract.getLeaseRequest().setLeasingContract(null);
            //leasingContract.getLeaseRequest().setTenant(null);
        }
    }
}