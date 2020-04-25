package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.app.model.Warehouse;

import java.util.List;

public interface WarehouseInterface {
    List<Warehouse> getWarehouses();

    List<Warehouse> getWarehousesForOwner(Long id);

    List<Warehouse> getWarehousesForSaleAgent(Long id);

    Warehouse getWarehouse(Long id) throws NullPointerException;

    Warehouse createWarehouse(Warehouse entity);

    Warehouse updateWarehouse(Warehouse entity) throws NullPointerException;

    void deleteWarehouse(Long id);
}
