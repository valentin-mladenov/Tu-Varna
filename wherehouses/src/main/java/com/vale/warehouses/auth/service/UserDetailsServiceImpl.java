package com.vale.warehouses.auth.service;

import com.vale.warehouses.auth.models.RoleEntity;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleEntity role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        // return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

//    private static Collection<? extends GrantedAuthority> getAuthorities(UserEntity user) {
//        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
//
//        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//
//        return authorities;
//    }
}
