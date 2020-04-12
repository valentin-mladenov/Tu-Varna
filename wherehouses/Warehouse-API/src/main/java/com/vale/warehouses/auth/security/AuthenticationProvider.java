package com.vale.warehouses.auth.security;

import com.vale.warehouses.auth.models.RoleEntity;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    UserRepository userRepository;

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
    ) throws AuthenticationException {
        //
    }

    @Override
    protected UserDetails retrieveUser(
            String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
    ) throws AuthenticationException {
        UserEntity user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleEntity role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
