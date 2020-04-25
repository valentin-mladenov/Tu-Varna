package com.vale.warehouses.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="warehouse")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public double width;

    public double length;

    public double height;

    public BigDecimal pricePerMonth;

    @Enumerated
    public WarehouseType type;

    @Enumerated
    public Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    public Owner owner = new Owner();

    @ManyToMany
    public Set<SaleAgent> saleAgents = new HashSet<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    public Set<LeasingContract> leasingContracts = new HashSet<>();

    public String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<SaleAgent> getSaleAgents() {
        return saleAgents;
    }

    public void setSaleAgents(Set<SaleAgent> agents) {
        this.saleAgents = agents;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }

    public BigDecimal getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(BigDecimal pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<LeasingContract> getLeasingContracts() {
        return leasingContracts;
    }

    public void setLeasingContracts(Set<LeasingContract> leasingContracts) {
        this.leasingContracts = leasingContracts;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
