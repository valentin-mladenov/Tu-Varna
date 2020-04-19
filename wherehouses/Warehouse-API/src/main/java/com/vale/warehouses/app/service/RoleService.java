package com.vale.warehouses.app.service;

import com.vale.warehouses.app.service.interfaces.RoleServiceInterface;
import com.vale.warehouses.auth.models.RoleEntity;
import com.vale.warehouses.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements RoleServiceInterface {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> getRoles()
    {
        List<RoleEntity> roleList = roleRepository.findAll();

        if(roleList.size() > 0) {
            return roleList;
        } else {
            return new ArrayList<>();
        }
    }
}
