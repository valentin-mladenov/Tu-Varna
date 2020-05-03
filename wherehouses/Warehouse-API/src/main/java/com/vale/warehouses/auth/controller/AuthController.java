package com.vale.warehouses.auth.controller;

import com.vale.warehouses.app.service.interfaces.UserServiceInterface;
import com.vale.warehouses.auth.models.TokenEntity;
import com.vale.warehouses.auth.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private AuthService authService;

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

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(TokenEntity token) {
        try {
            authService.logout(token.getId());
        }
        catch (Exception ex){
            logger.error("Logout Unsuccessful \r\n" + ex.toString());
        }

        return ResponseEntity.ok().body(true);
    }
}
