package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.app.model.LeasingContract;

import java.util.List;

public interface LeasingContractInterface {
    List<LeasingContract> getLeasingContracts();

    LeasingContract getLeasingContract(Long id) throws NullPointerException;

    LeasingContract createLeasingContract(LeasingContract entity);

    LeasingContract updateLeasingContract(LeasingContract entity) throws NullPointerException;

    void deleteLeasingContract(Long id);
}
