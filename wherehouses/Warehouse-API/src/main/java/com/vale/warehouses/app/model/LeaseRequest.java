package com.vale.warehouses.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="lease_request")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LeaseRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Tenant tenant = new Tenant();

    @OneToOne(
            fetch = FetchType.LAZY,
            mappedBy = "leaseRequest",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private LeasingContract leasingContract;

    @Column(name = "warehouse_type")
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
