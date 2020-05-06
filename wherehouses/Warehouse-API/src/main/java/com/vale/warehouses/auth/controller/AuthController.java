package com.vale.warehouses.auth.controller;

import com.vale.warehouses.app.model.LeaseRequest;
import com.vale.warehouses.app.service.interfaces.LeaseRequestInterface;
import com.vale.warehouses.app.service.interfaces.UserServiceInterface;
import com.vale.warehouses.auth.models.TokenEntity;
import com.vale.warehouses.auth.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private LeaseRequestInterface leaseRequestService;

    @PostMapping("/login")
    public TokenEntity login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password
    ) {
        try {
            TokenEntity token = authService.login(username, password);

            // hide password
            token.getUser().setPassword(null);
            token.getUser().setPasswordConfirm(null);

            if(token.getUser().getRelatedSaleAgent() != null) {
                token.getUser().getRelatedSaleAgent().setWarehouses(new HashSet<>());
            }

            token.getUser().getRoles().forEach((r) -> {
                r.setUsers(null);
            });

            return token;
        }
        catch (Exception ex){
            logger.error("Login Unsuccessful \r\n" + ex.toString());
            throw ex;
        }
    }

    @PostMapping("/createLeaseRequest")
    public ResponseEntity<?> saveLeaseRequest(@RequestBody LeaseRequest leaseRequest) {
        try {
            leaseRequest = leaseRequestService.createLeaseRequest(leaseRequest);

            return ResponseEntity.ok(leaseRequest);
        } catch (Exception ex) {
            logger.error(ex.toString());

            throw ex;
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(TokenEntity token) {
        try {
            authService.logout(token.getUUID());
        }
        catch (Exception ex){
            logger.error("Logout Unsuccessful \r\n" + ex.toString());
        }

        return ResponseEntity.ok().body(true);
    }
}
