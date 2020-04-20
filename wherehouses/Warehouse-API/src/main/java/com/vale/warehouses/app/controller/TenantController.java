package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.Tenant;
import com.vale.warehouses.app.service.interfaces.TenantInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    @Autowired
    private TenantInterface service;

    /*---get all tenants---*/
    @GetMapping
    public ResponseEntity<List<Tenant>> list() {
        List<Tenant> tenants = service.getTenants();

        return ResponseEntity.ok(tenants);
    }

    /*---Get a tenant by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<Tenant> get(@PathVariable("id") long id) {
        Tenant tenant = service.getTenant(id);

        return ResponseEntity.ok(tenant);
    }

//    /*---Add new tenant---*/
//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody Tenant tenant) {
//        tenant = service.createTenant(tenant);
//
//        return ResponseEntity.ok(tenant);
//    }

    /*---Update a tenant by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<Tenant> update(@PathVariable("id") long id, @RequestBody Tenant tenant) {
        service.updateTenant(tenant);

        return  ResponseEntity.ok(tenant);
    }

    /*---Delete a tenant by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.deleteTenant(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}