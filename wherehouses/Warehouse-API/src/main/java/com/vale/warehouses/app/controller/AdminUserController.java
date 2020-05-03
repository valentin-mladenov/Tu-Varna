package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.service.AdminUserService;
import com.vale.warehouses.auth.models.RoleType;
import com.vale.warehouses.auth.models.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AdminUserController {
    private static final Logger logger = LogManager.getLogger(AdminUserController.class);

    @Autowired
    private AdminUserService userService;


    /*---get all users---*/
    @GetMapping
    public ResponseEntity<List<UserEntity>> list() {
        throwExceptionIfNotAdminUser();

        try {
            List<UserEntity> users = userService.getUsers();

            for (UserEntity user : users) {
                this.hideSensitiveData(user);
                this.nullifyData(user);
            }

            return ResponseEntity.ok().body(users);
        } catch (Exception ex) {
            logger.error("User get list: " + ex.getMessage() + "\r\n" + ex.toString());

            throw ex;
        }
    }

    /*---Get a user by id---*/
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable("id") long id) {
        throwExceptionIfNotAdminUser();

        try {
            UserEntity user = userService.getUser(id);

            this.hideSensitiveData(user);
            this.nullifyData(user);

            return ResponseEntity.ok().body(user);
        } catch (Exception ex) {
            logger.error("User get id: " + ex.getMessage() + "\r\n" + ex.toString());

            throw ex;
        }
    }

    /*---Add new user---*/
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserEntity user) {
        throwExceptionIfNotAdminUser();

        try {
            UserEntity userResult = userService.createUser(user);

            this.hideSensitiveData(userResult);
            this.nullifyData(user);

            return ResponseEntity.ok().body(userResult);
        }
        catch (Exception ex){
            logger.error("User save new: " + ex.getMessage() + "\r\n" + ex.toString());

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

        try {
            UserEntity userResult = userService.updateUser(id, user);

            this.hideSensitiveData(userResult);
            this.nullifyData(user);

            return ResponseEntity.ok().body(userResult);
        } catch (Exception ex) {
            logger.error("User update: " + ex.getMessage() + "\r\n" + ex.toString());

            throw ex;
        }
    }

    /*---Delete a user by id---*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        throwExceptionIfNotAdminUser();

        try {
            userService.deleteUser(id);

            return (ResponseEntity<?>) ResponseEntity.noContent();
        }
        catch (Exception ex) {
            logger.error("User delete" + ex.getMessage() + "\r\n" + ex.toString() );

            throw ex;
        }
    }

    private void throwExceptionIfNotAdminUser() throws AccessDeniedException {
        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals(RoleType.Admin.toString()));

        if (!isAdmin) {
            logger.error("Admins only" );
            throw new AccessDeniedException("Admins only");
        }
    }

    private void nullifyData(UserEntity userResult) {
        if(userResult.getRelatedSaleAgent() != null) {
            userResult.getRelatedSaleAgent().setWarehouses(new HashSet<>());
        }
    }

    private void hideSensitiveData(UserEntity userResult) {
        userResult.setPassword(null);
        userResult.setPasswordConfirm(null);
    }
}