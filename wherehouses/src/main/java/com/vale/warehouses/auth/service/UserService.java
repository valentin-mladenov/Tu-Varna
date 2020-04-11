package com.vale.warehouses.auth.service;

import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.RoleRepository;
import com.vale.warehouses.auth.repository.UserRepository;
import com.vale.warehouses.auth.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity save(UserEntity user) {
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            throw new IllegalArgumentException("PAss and pass confirm doesn't match");
        }

        if (user.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User need at least 1 role");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPasswordConfirm("");
        user = userRepository.save(user);

        return user;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
