package com.vale.warehouses.app.service;

import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.UserRepository;
import com.vale.warehouses.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {
    @Autowired
    UserRepository repository;

    @Autowired
    UserService userService;

    public List<UserEntity> getUsers()
    {
        List<UserEntity> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    public UserEntity getUser(Long id) throws NullPointerException
    {
        Optional<UserEntity> user = repository.findById(id);

        if(!user.isPresent()) {
            throw new NullPointerException("No user record exist for given id");
        }

        return user.get();
    }

    public UserEntity createUser(UserEntity entity)
    {
        // Optional<UserEntity> user = repository.findById(entity.getId());

        if(repository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("User with that ID already exists");
        }

        entity = userService.save(entity);

        return entity;
    }

    public UserEntity updateUser(UserEntity entity) throws NullPointerException
    {
        Optional<UserEntity> user = repository.findById(entity.getId());

        if(!user.isPresent())
        {
            throw new NullPointerException("No user with that ID");
        }

        UserEntity newEntity = user.get();
        newEntity.setEmail(entity.getEmail());

        newEntity = repository.save(newEntity);

        return newEntity;
    }

    public void deleteUser(Long id) throws NullPointerException
    {
        Optional<UserEntity> user = repository.findById(id);

        if(!user.isPresent())
        {
            throw new NullPointerException("No user record exist for given id");
        }

        repository.deleteById(id);
    }
}
