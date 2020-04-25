package com.vale.warehouses.app.service;

import com.vale.warehouses.app.model.Warehouse;
import com.vale.warehouses.app.repository.WarehouseRepository;
import com.vale.warehouses.app.service.interfaces.WarehouseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WarehouseService implements WarehouseInterface {
    @Autowired
    private WarehouseRepository repository;

    @Override
    public List<Warehouse> getWarehouses()
    {
        List<Warehouse> warehouseList = repository.findAll();

        if(warehouseList.size() > 0) {
            return warehouseList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Warehouse> getWarehousesForOwner(Long id) {
        List<Warehouse> warehouseList = repository.findByOwnerId(id);

        if(warehouseList.size() > 0) {
            return warehouseList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Warehouse> getWarehousesForSaleAgent(Long id) {
        List<Warehouse> warehouseList = repository.findBySaleAgentsId(id);

        if(warehouseList.size() > 0) {
            return warehouseList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Warehouse getWarehouse(Long id) throws NullPointerException
    {
        Optional<Warehouse> user = repository.findById(id);

        if(!user.isPresent()) {
            throw new NullPointerException("No record exist for given id");
        }

        return user.get();
    }

    @Override
    public Warehouse createWarehouse(Warehouse entity)
    {
        if(!Objects.isNull(entity.getId()) && repository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("Record with that ID already exists");
        }

        return repository.save(entity);
    }

    @Override
    public Warehouse updateWarehouse(Warehouse entity) throws NullPointerException
    {
        Optional<Warehouse> user = repository.findById(entity.getId());

        if(!user.isPresent())
        {
            throw new NullPointerException("No record with that ID");
        }

        return repository.save(entity);
    }

    @Override
    public void deleteWarehouse(Long id) throws NullPointerException
    {
        Optional<Warehouse> user = repository.findById(id);

        if(!user.isPresent())
        {
            throw new NullPointerException("No record exist for given id");
        }

        repository.deleteById(id);
    }
}
