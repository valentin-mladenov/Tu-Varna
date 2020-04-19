package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.auth.models.RoleEntity;

import java.util.List;

public interface RoleServiceInterface {
    List<RoleEntity> getRoles();
}
