package com.vale.warehouses.auth.service.interfaces;

import com.vale.warehouses.auth.models.TokenEntity;
import com.vale.warehouses.auth.models.UserEntity;
import org.springframework.security.core.AuthenticationException;

import java.util.UUID;

public interface AuthServiceInterface {
    TokenEntity login(String username, String password) throws AuthenticationException;

    void logout(UUID tokenId);

    boolean isTokenValid(UUID tokenId);

    UserEntity findUserByToken(UUID tokenId);
}
