package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.auth.models.UserEntity;

import java.util.List;

public interface UserServiceInterface {
    UserEntity findByUsername(String username);

    List<UserEntity> getUsers();

    UserEntity getUser(Long id) throws NullPointerException;

    UserEntity createUser(UserEntity entity);

    UserEntity updateUser(Long id, UserEntity entity) throws NullPointerException;

    void deleteUser(Long id);
}
