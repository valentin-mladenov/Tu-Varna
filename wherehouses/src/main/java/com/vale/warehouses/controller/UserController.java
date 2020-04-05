package com.vale.warehouses.controller;
import java.util.List;

import com.vale.warehouses.model.UserEntity;
import com.vale.warehouses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*---get all books---*/
    @GetMapping
    public ResponseEntity<List<UserEntity>> list() {
        List<UserEntity> users = userService.getUsers();

        return ResponseEntity.ok().body(users);
    }

    /*---Get a book by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") long id) {
        UserEntity user = userService.getUser(id);

        return ResponseEntity.ok().body(user);
    }

    /*---Add new book---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        UserEntity userResult = userService.createUser(user);

        return ResponseEntity.ok().body(userResult);
    }

    /*---Update a book by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody UserEntity user) {
        userService.updateUser(user);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }

    /*---Delete a book by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        userService.deleteUser(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }
}
