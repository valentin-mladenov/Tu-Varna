package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.app.model.Owner;

import java.util.List;

public interface OwnerInterface {
    List<Owner> getOwners();

    Owner getOwner(Long id) throws NullPointerException;

    Owner createOwner(Owner entity);

    Owner updateOwner(Owner entity) throws NullPointerException;

    void deleteOwner(Long id);
}
