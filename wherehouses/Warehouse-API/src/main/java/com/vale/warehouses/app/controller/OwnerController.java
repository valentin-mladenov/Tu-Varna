package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.Owner;
import com.vale.warehouses.app.service.interfaces.OwnerInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    @Autowired
    private OwnerInterface service;

    private static final Logger logger = LogManager.getLogger(AdminUserController.class);

    /*---get all owners---*/
    @GetMapping
    public ResponseEntity<List<Owner>> list() {
        try {
            List<Owner> owners = service.getOwners();

            return ResponseEntity.ok(owners);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Get a owner by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<Owner> get(@PathVariable("id") long id) {
        try {
            Owner owner = service.getOwner(id);

            return ResponseEntity.ok(owner);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Update a owner by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(
            @PathVariable("id") long id,
            @RequestBody Owner owner) {
        try {
            service.updateOwner(owner);

            return ResponseEntity.ok(owner);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    /*---Delete a owner by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            service.deleteOwner(id);

            return (ResponseEntity<?>) ResponseEntity.noContent();
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }
}