package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.app.model.Tenant;

import java.util.List;

public interface TenantInterface {
    List<Tenant> getTenants();

    Tenant getTenant(Long id) throws NullPointerException;

    Tenant createTenant(Tenant entity);

    Tenant updateTenant(Tenant entity) throws NullPointerException;

    void deleteTenant(Long id);
}
