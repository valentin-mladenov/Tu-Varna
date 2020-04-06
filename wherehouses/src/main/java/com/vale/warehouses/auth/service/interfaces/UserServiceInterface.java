package com.vale.warehouses.auth.service.interfaces;

import com.vale.warehouses.auth.models.UserEntity;

public interface UserServiceInterface {
    void save(UserEntity user);

    UserEntity findByUsername(String username);
}
