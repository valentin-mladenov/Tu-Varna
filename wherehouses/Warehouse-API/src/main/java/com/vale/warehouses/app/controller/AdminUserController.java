package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.service.AdminUserService;
import com.vale.warehouses.auth.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

        for (UserEntity user : users) {
            this.hideSensitiveData(user);
        }

        return ResponseEntity.ok().body(users);
    }

    /*---Get a user by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") long id) {
        throwExceptionIfNotAdminUser();

        UserEntity user = userService.getUser(id);

        this.hideSensitiveData(user);

        return ResponseEntity.ok().body(user);
    }

    /*---Add new user---*/
    // @PostMapping(consumes = MediaType.ALL_VALUE)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        throwExceptionIfNotAdminUser();

        try {
            UserEntity userResult = userService.createUser(user);

            this.hideSensitiveData(userResult);

            return ResponseEntity.ok().body(userResult);
        }
        catch (Exception ex){
            throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                ex.getMessage(),
                ex
            );
        }
    }

    /*---Update a user by id---*/
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody UserEntity user) {
        throwExceptionIfNotAdminUser();

        UserEntity userResult = userService.updateUser(id, user);

        this.hideSensitiveData(userResult);

        return ResponseEntity.ok().body(userResult);
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

    private void hideSensitiveData(UserEntity userResult) {
        userResult.setPassword(null);
        userResult.setPasswordConfirm(null);
    }
}