package com.vale.warehouses.app.model;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name="leasing_contract")
public class LeasingContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="leased_at")
    private OffsetDateTime leasedAt;

    @Column(name="leased_till")
    private OffsetDateTime leasedTill;

    @ManyToOne(fetch = FetchType.LAZY)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private SaleAgent saleAgent;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getLeasedAt() {
        return leasedAt;
    }

    public void setLeasedAt(OffsetDateTime leasedAt) {
        this.leasedAt = leasedAt;
    }

    public OffsetDateTime getLeasedTill() {
        return leasedTill;
    }

    public void setLeasedTill(OffsetDateTime leasedTill) {
        this.leasedTill = leasedTill;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public SaleAgent getSaleAgent() {
        return saleAgent;
    }

    public void setSaleAgent(SaleAgent saleAgent) {
        this.saleAgent = saleAgent;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
