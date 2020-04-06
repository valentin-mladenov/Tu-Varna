package com.vale.warehouses.controller;

import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AdminUserController {
    @Autowired
    private AdminUserService userService;

    /*---get all users---*/
    @GetMapping
    public ResponseEntity<List<UserEntity>> list() {
        throwExceptionIfNotAdminUser();

        List<UserEntity> users = userService.getUsers();

        // hide password
        for (UserEntity user : users) {
            user.setPassword("");
        }

        return ResponseEntity.ok().body(users);
    }

    /*---Get a user by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") long id) {
        throwExceptionIfNotAdminUser();

        UserEntity user = userService.getUser(id);

        // hide password
        user.setPassword("");

        return ResponseEntity.ok().body(user);
    }

    /*---Add new user---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        throwExceptionIfNotAdminUser();

        UserEntity userResult = userService.createUser(user);

        return ResponseEntity.ok().body(userResult);
    }

    /*---Update a user by id---*/
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody UserEntity user) {
        throwExceptionIfNotAdminUser();

        userService.updateUser(user);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }

    /*---Delete a user by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        throwExceptionIfNotAdminUser();

        userService.deleteUser(id);

        return (ResponseEntity<?>) ResponseEntity.noContent();
    }

    private void throwExceptionIfNotAdminUser() throws AccessDeniedException {
        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().toLowerCase().equals("admin"));

        if (!isAdmin) {
            throw new AccessDeniedException("Admins only");
        }
    }
}