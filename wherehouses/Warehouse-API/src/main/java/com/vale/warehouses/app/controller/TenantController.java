package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.Tenant;
import com.vale.warehouses.app.service.interfaces.TenantInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    @Autowired
    private TenantInterface service;

    private static final Logger logger = LogManager.getLogger(TenantController.class);

    /*---get all tenants---*/
    @GetMapping
    public ResponseEntity<List<Tenant>> list() {
        try {
            List<Tenant> tenants = service.getTenants();

            return ResponseEntity.ok(tenants);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Get a tenant by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<Tenant> get(@PathVariable("id") long id) {
        try {
            Tenant tenant = service.getTenant(id);

            return ResponseEntity.ok(tenant);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Update a tenant by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<Tenant> update(
            @PathVariable("id") long id,
            @RequestBody Tenant tenant) {
        try {
            service.updateTenant(tenant);

            return ResponseEntity.ok(tenant);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Delete a tenant by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            service.deleteTenant(id);

            return (ResponseEntity<?>) ResponseEntity.noContent();
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }
}