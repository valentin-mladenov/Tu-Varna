package com.vale.warehouses.app.service;

import com.vale.warehouses.app.model.SaleAgent;
import com.vale.warehouses.app.repository.SaleAgentRepository;
import com.vale.warehouses.app.service.interfaces.SaleAgentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SaleAgentService implements SaleAgentInterface {
    @Autowired
    private SaleAgentRepository repository;

    @Override
    public List<SaleAgent> getSaleAgents()
    {
        List<SaleAgent> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public SaleAgent getSaleAgent(Long id) throws NullPointerException
    {
        Optional<SaleAgent> user = repository.findById(id);

        if(!user.isPresent()) {
            throw new NullPointerException("No record exist for given id");
        }

        return user.get();
    }

    @Override
    public SaleAgent createSaleAgent(SaleAgent entity)
    {
        if(!Objects.isNull(entity.getId()) && repository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("Record with that ID already exists");
        }

        return repository.save(entity);
    }

    @Override
    public SaleAgent updateSaleAgent(SaleAgent entity) throws NullPointerException
    {
        Optional<SaleAgent> user = repository.findById(entity.getId());

        if(!user.isPresent())
        {
            throw new NullPointerException("No record with that ID");
        }

        return repository.save(entity);
    }

    @Override
    public void deleteSaleAgent(Long id) throws NullPointerException
    {
        Optional<SaleAgent> user = repository.findById(id);

        if(!user.isPresent())
        {
            throw new NullPointerException("No record exist for given id");
        }

        repository.deleteById(id);
    }
}
