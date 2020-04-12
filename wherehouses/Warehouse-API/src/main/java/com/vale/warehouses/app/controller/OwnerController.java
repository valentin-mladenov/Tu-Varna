package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.model.Owner;
import com.vale.warehouses.app.service.interfaces.OwnerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    @Autowired
    private OwnerInterface service;

    /*---get all owners---*/
    @GetMapping
    public ResponseEntity<List<Owner>> list() {
        List<Owner> owners = service.getOwners();

        return ResponseEntity.ok(owners);
    }

    /*---Get a owner by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<Owner> get(@PathVariable("id") long id) {
        Owner owner = service.getOwner(id);

        return ResponseEntity.ok(owner);
    }

    /*---Add new owner---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Owner owner) {
        owner = service.createOwner(owner);

        return ResponseEntity.ok(owner);
    }

    /*---Update a owner by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(@PathVariable("id") long id, @RequestBody Owner owner) {
        service.updateOwner(owner);

        return  ResponseEntity.ok(owner);
    }

    /*---Delete a owner by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.deleteOwner(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}