package com.vale.warehouses.app.service;

import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.repository.LeasingContractRepository;
import com.vale.warehouses.app.service.interfaces.LeasingContractInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LeasingContractService implements LeasingContractInterface {
    @Autowired
    private LeasingContractRepository repository;

    @Override
    public List<LeasingContract> getLeasingContracts()
    {
        List<LeasingContract> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public LeasingContract getLeasingContract(Long id) throws NullPointerException
    {
        Optional<LeasingContract> user = repository.findById(id);

        if(!user.isPresent()) {
            throw new NullPointerException("No record exist for given id");
        }

        return user.get();
    }

    @Override
    public LeasingContract createLeasingContract(LeasingContract entity)
    {
        if(!Objects.isNull(entity.getId()) && repository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("Record with that ID already exists");
        }

        return repository.save(entity);
    }

    @Override
    public LeasingContract updateLeasingContract(LeasingContract entity) throws NullPointerException
    {
        Optional<LeasingContract> user = repository.findById(entity.getId());

        if(!user.isPresent())
        {
            throw new NullPointerException("No record with that ID");
        }

        return repository.save(entity);
    }

    @Override
    public void deleteLeasingContract(Long id) throws NullPointerException
    {
        Optional<LeasingContract> user = repository.findById(id);

        if(!user.isPresent())
        {
            throw new NullPointerException("No record exist for given id");
        }

        repository.deleteById(id);
    }
}
