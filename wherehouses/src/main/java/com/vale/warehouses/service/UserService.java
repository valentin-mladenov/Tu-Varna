package com.vale.warehouses.service;

import com.vale.warehouses.model.UserEntity;
import com.vale.warehouses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<UserEntity> getUsers()
    {
        List<UserEntity> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<UserEntity>();
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
        Optional<UserEntity> user = repository.findById(entity.getId());

        if(user.isPresent())
        {
            throw new IllegalArgumentException("User with that ID already exists");
        }

        entity = repository.save(entity);

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
        newEntity.setFirstName(entity.getFirstName());
        newEntity.setLastName(entity.getLastName());

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
