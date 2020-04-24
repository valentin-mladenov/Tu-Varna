package com.vale.warehouses.app.view_model;

import com.vale.warehouses.app.model.AbstractPerson;
import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.model.Warehouse;
import com.vale.warehouses.auth.models.UserEntity;

import java.util.Set;

public class InputOwner extends AbstractPerson {
    private Set<Warehouse> warehouses;

    private Set<LeasingContract> leasingContracts;

    private UserEntity user;

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Set<LeasingContract> getLeasingContracts() {
        return leasingContracts;
    }

    public void setLeasingContracts(Set<LeasingContract> leasingContracts) {
        this.leasingContracts = leasingContracts;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
