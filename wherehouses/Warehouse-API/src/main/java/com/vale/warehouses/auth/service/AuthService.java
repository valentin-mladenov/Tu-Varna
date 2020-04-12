package com.vale.warehouses.auth.service;

import com.vale.warehouses.auth.models.TokenEntity;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.TokenRepository;
import com.vale.warehouses.auth.repository.UserRepository;
import com.vale.warehouses.auth.service.interfaces.AuthServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class AuthService implements AuthServiceInterface {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public TokenEntity login(String username, String password) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (!usernamePasswordAuthenticationToken.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("Username and/or password are incorrect.");
        }

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        TokenEntity token = new TokenEntity();

        UserEntity user = userRepository.findByUsername(username);

        token.setId(UUID.randomUUID());
        token.setExpireAt((OffsetDateTime.now()).plusHours(1));
        token.setUser(user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public void logout(UUID tokenId) {
        tokenRepository.deleteById(tokenId.toString());
    }

    @Override
    public boolean isTokenValid(UUID tokenId) {
        boolean comparison = tokenRepository.getOne(tokenId.toString()).getExpireAt().compareTo(OffsetDateTime.now()) > 0;

        return comparison;
    }

    @Override
    public UserEntity findUserByToken(UUID tokenId) {
        UserEntity user = tokenRepository.getOne(tokenId.toString()).getUser();

        return user;
    }
}
