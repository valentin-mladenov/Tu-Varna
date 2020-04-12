package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.app.model.Warehouse;

import java.util.List;

public interface WarehouseInterface {
    List<Warehouse> getWarehouses();

    Warehouse getWarehouse(Long id) throws NullPointerException;

    Warehouse createWarehouse(Warehouse entity);

    Warehouse updateWarehouse(Warehouse entity) throws NullPointerException;

    void deleteWarehouse(Long id);
}
