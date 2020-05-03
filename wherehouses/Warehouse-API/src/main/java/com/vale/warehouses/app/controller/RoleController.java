package com.vale.warehouses.app.controller;

import com.vale.warehouses.app.service.RoleService;
import com.vale.warehouses.auth.models.RoleEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    private static final Logger logger = LogManager.getLogger(RoleController.class);

    /*---get all roles---*/
    @GetMapping
    public ResponseEntity<List<RoleEntity>> list() {
        try {
            throwExceptionIfNotRole();

            List<RoleEntity> roles = roleService.getRoles();

            for (RoleEntity role : roles) {
                role.setUsers(null);
            }

            return ResponseEntity.ok().body(roles);
        }
        catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    private void throwExceptionIfNotRole() throws AccessDeniedException {
        try {
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
        catch(Exception ex){
            logger.error(ex.toString());

            throw ex;
        }
    }
}