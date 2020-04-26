package com.vale.warehouses.auth.controller;

import com.vale.warehouses.auth.models.TokenEntity;
import com.vale.warehouses.auth.service.AuthService;
import com.vale.warehouses.app.service.interfaces.UserServiceInterface;
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
    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public TokenEntity login(
            @RequestParam("username") final String username,
            @RequestParam("password") final String password
    ) {
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

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(TokenEntity token) {
        try {
            authService.logout(token.getId());
        }
        catch (Exception ex){
            // TODO log error
        }

        return ResponseEntity.ok().body(true);
    }
}
