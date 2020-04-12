package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.app.model.SaleAgent;

import java.util.List;

public interface SaleAgentInterface {
    List<SaleAgent> getSaleAgents();

    SaleAgent getSaleAgent(Long id) throws NullPointerException;

    SaleAgent createSaleAgent(SaleAgent entity);

    SaleAgent updateSaleAgent(SaleAgent entity) throws NullPointerException;

    void deleteSaleAgent(Long id);
}
