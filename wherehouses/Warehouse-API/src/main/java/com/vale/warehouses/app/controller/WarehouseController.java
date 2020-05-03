package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.model.Warehouse;
import com.vale.warehouses.app.service.interfaces.LeasingContractInterface;
import com.vale.warehouses.app.service.interfaces.WarehouseInterface;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    private static final Logger logger = LogManager.getLogger(WarehouseController.class);

    @Autowired
    private WarehouseInterface service;

    @Autowired
    private LeasingContractInterface leasingContractService;

    /*---get all warehouses---*/
    @GetMapping
    public ResponseEntity<List<Warehouse>> list() {
        try {
            throwExceptionIfAccessForbidden(RoleType.Admin);

            List<Warehouse> warehouses = service.getWarehouses();

            for (Warehouse warehouse : warehouses) {
                warehouse.setSaleAgents(new HashSet<>());
                warehouse.getOwner().setWarehouses(null);
            }

            return ResponseEntity.ok().body(warehouses);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---get all warehouses---*/
    @GetMapping("forOwner/{id}")
    public ResponseEntity<List<Warehouse>> listForOwner(@PathVariable("id") long id) {
        try {
            throwExceptionIfAccessForbidden(RoleType.Owner);

            List<Warehouse> warehouses = service.getWarehousesForOwner(id);

            for (Warehouse warehouse : warehouses) {
                warehouse.setSaleAgents(new HashSet<>());
                warehouse.getOwner().setWarehouses(null);
            }

            return ResponseEntity.ok().body(warehouses);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---get all warehouses---*/
    @GetMapping("forSaleAgent/{id}")
    public ResponseEntity<List<Warehouse>> listForSaleAgent(@PathVariable("id") long id) {
        try {
            throwExceptionIfAccessForbidden(RoleType.Agent);

            List<Warehouse> warehouses = service.getWarehousesForSaleAgent(id);

            for (Warehouse warehouse : warehouses) {
                warehouse.setSaleAgents(new HashSet<>());
                warehouse.getOwner().setWarehouses(null);
            }

            return ResponseEntity.ok().body(warehouses);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---get all warehouses---*/
    @GetMapping("allFreeForSaleAgent/{id}")
    public ResponseEntity<List<Warehouse>> listAllForSaleAgent(
            @PathVariable("id") long id,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) OffsetDateTime fromDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) OffsetDateTime toDate
    ) {
        try {
            if (fromDate == null) {
                fromDate = OffsetDateTime.now();
                fromDate = fromDate.minusYears(100);
            }

            if (toDate == null) {
                toDate = fromDate.plusYears(200);
            }

            throwExceptionIfAccessForbidden(RoleType.Agent);

            Set<Long> ids = new HashSet<>();

            Set<Warehouse> allWarehouses = new HashSet<>(service.getWarehousesForSaleAgent(id));
            allWarehouses.forEach(w -> ids.add(w.getId()));

            List<LeasingContract> leasingContracts = leasingContractService
                    .getLeasingContractsForWarehouse(ids, fromDate, toDate);

            Set<Warehouse> warehouses = new HashSet<>();
            leasingContracts.forEach(lc -> warehouses.add(lc.getWarehouse()));

            allWarehouses.removeAll(warehouses);

            for (Warehouse warehouse : allWarehouses) {
                warehouse.setSaleAgents(new HashSet<>());
                warehouse.getOwner().setWarehouses(null);
            }

            return ResponseEntity.ok().body(new ArrayList<>(allWarehouses));
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Get a warehouse by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> get(@PathVariable("id") long id) {
        try {
            Warehouse warehouse = service.getWarehouse(id);

            warehouse.getSaleAgents().forEach(saleAgent -> saleAgent.setWarehouses(new HashSet<>()));

            return ResponseEntity.ok(warehouse);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Add new warehouse---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Warehouse warehouse) {
        try {
            throwExceptionIfAccessForbidden(RoleType.Owner);

            warehouse = service.createWarehouse(warehouse);

            warehouse.getSaleAgents().forEach(saleAgent -> saleAgent.setWarehouses(new HashSet<>()));

            return ResponseEntity.ok(warehouse);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Update a warehouse by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> update(
            @PathVariable("id") long id,
            @RequestBody Warehouse warehouse
    ) {
        try {
            throwExceptionIfAccessForbidden(RoleType.Owner);

            service.updateWarehouse(warehouse);

            return ResponseEntity.ok(warehouse);
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
    }

    /*---Delete a warehouse by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            throwExceptionIfAccessForbidden(RoleType.Owner);

            service.deleteWarehouse(id);

            return (ResponseEntity<?>) ResponseEntity.noContent();
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw ex;
        }
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