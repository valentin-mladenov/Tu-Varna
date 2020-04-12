package com.vale.warehouses.app.service;

import com.vale.warehouses.app.service.interfaces.UserServiceInterface;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService implements UserServiceInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserEntity> getUsers()
    {
        List<UserEntity> employeeList = userRepository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public UserEntity getUser(Long id) throws NullPointerException
    {
        Optional<UserEntity> user = userRepository.findById(id);

        if(!user.isPresent()) {
            throw new NullPointerException("No user record exist for given id");
        }

        return user.get();
    }

    @Override
    public UserEntity createUser(UserEntity entity)
    {
        // Optional<UserEntity> user = repository.findById(entity.getId());

        if(userRepository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("User with that ID already exists");
        }


        if (!entity.getPassword().equals(entity.getPasswordConfirm())) {
            throw new IllegalArgumentException("PAss and pass confirm doesn't match");
        }

        if (entity.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User need at least 1 role");
        }

        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        entity.setPasswordConfirm("");

        return userRepository.save(entity);
    }

    @Override
    public UserEntity updateUser(UserEntity entity) throws NullPointerException
    {
        Optional<UserEntity> user = userRepository.findById(entity.getId());

        if(!user.isPresent())
        {
            throw new NullPointerException("No user with that ID");
        }

        UserEntity newEntity = user.get();
        newEntity.setEmail(entity.getEmail());

        return userRepository.save(newEntity);
    }

    @Override
    public void deleteUser(Long id) throws NullPointerException
    {
        Optional<UserEntity> user = userRepository.findById(id);

        if(!user.isPresent())
        {
            throw new NullPointerException("No user record exist for given id");
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
