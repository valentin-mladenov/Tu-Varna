package com.vale.warehouses.app.service;

import com.vale.warehouses.app.model.Tenant;
import com.vale.warehouses.app.repository.TenantRepository;
import com.vale.warehouses.app.service.interfaces.TenantInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService implements TenantInterface {
    @Autowired
    private TenantRepository repository;

    @Override
    public List<Tenant> getTenants()
    {
        List<Tenant> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Tenant getTenant(Long id) throws NullPointerException
    {
        Optional<Tenant> user = repository.findById(id);

        if(!user.isPresent()) {
            throw new NullPointerException("No record exist for given id");
        }

        return user.get();
    }

    @Override
    public Tenant createTenant(Tenant entity)
    {
        if(repository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("Record with that ID already exists");
        }

        return repository.save(entity);
    }

    @Override
    public Tenant updateTenant(Tenant entity) throws NullPointerException
    {
        Optional<Tenant> user = repository.findById(entity.getId());

        if(!user.isPresent())
        {
            throw new NullPointerException("No record with that ID");
        }

        return repository.save(entity);
    }

    @Override
    public void deleteTenant(Long id) throws NullPointerException
    {
        Optional<Tenant> user = repository.findById(id);

        if(!user.isPresent())
        {
            throw new NullPointerException("No record exist for given id");
        }

        repository.deleteById(id);
    }
}
