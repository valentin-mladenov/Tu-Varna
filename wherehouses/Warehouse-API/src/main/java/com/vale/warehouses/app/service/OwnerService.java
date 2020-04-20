package com.vale.warehouses.app.service;

import com.vale.warehouses.app.model.Owner;
import com.vale.warehouses.app.repository.OwnerRepository;
import com.vale.warehouses.app.service.interfaces.OwnerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OwnerService implements OwnerInterface {
    @Autowired
    private OwnerRepository repository;

    @Override
    public List<Owner> getOwners()
    {
        List<Owner> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Owner getOwner(Long id) throws NullPointerException
    {
        Optional<Owner> user = repository.findById(id);

        if(!user.isPresent()) {
            throw new NullPointerException("No record exist for given id");
        }

        return user.get();
    }

    @Override
    public Owner createOwner(Owner entity)
    {
        if(!Objects.isNull(entity.getId()) && repository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("Record with that ID already exists");
        }

        return repository.save(entity);
    }

    @Override
    public Owner updateOwner(Owner entity) throws NullPointerException
    {
        Optional<Owner> user = repository.findById(entity.getId());

        if(!user.isPresent())
        {
            throw new NullPointerException("No record with that ID");
        }

        return repository.save(entity);
    }

    @Override
    public void deleteOwner(Long id) throws NullPointerException
    {
        Optional<Owner> user = repository.findById(id);

        if(!user.isPresent())
        {
            throw new NullPointerException("No record exist for given id");
        }

        repository.deleteById(id);
    }
}
