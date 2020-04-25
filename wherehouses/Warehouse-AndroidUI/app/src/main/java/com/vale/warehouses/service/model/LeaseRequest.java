package com.vale.warehouses.service.model;

import com.google.gson.annotations.SerializedName;

public class LeaseRequest {
    @SerializedName("id")
    private Long id;

    @SerializedName("tenant")
    private Tenant tenant;

    @SerializedName("leasingContract")
    private LeasingContract leasingContract;

    @SerializedName("warehouseType")
    private WarehouseType warehouseType;

    public WarehouseType getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(WarehouseType warehouseType) {
        this.warehouseType = warehouseType;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LeasingContract getLeasingContract() {
        return leasingContract;
    }

    public void setLeasingContract(LeasingContract leasingContract) {
        this.leasingContract = leasingContract;
    }
}
