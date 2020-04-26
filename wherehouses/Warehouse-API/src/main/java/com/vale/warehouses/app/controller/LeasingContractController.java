package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.service.interfaces.LeasingContractInterface;
import com.vale.warehouses.auth.models.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/leasingContract")
public class LeasingContractController {
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
            @RequestParam(required = false) OffsetDateTime fromDate,
            @RequestParam(required = false) OffsetDateTime toDate
    ) {
        if (fromDate == null) {
            fromDate = OffsetDateTime.now();
            fromDate = fromDate.minusYears(100);
        }

        if (toDate == null) {
            toDate = OffsetDateTime.now();
            toDate = toDate.plusYears(100);
        }

        throwExceptionIfAccessForbidden(RoleType.Agent);

        List<LeasingContract> leasingContracts = service.getLeasingContractsForSaleAgent(id, fromDate, toDate);

        for (LeasingContract leasingContract: leasingContracts) {
            nullifyNestedObjects(leasingContract);
        }

        return ResponseEntity.ok(leasingContracts);
    }

    /*---get all warehouses---*/
    @GetMapping("endingSoon/forSaleAgent/{id}")
    public ResponseEntity<List<LeasingContract>> endingSoonForSaleAgent(@PathVariable("id") long id) {
        throwExceptionIfAccessForbidden(RoleType.Agent);

        OffsetDateTime monthFromNow = OffsetDateTime.now().plusMonths(1).plusDays(3);

        List<LeasingContract> leasingContracts = service.getEndingSoonLeasingContractsForSaleAgent(id, monthFromNow);

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