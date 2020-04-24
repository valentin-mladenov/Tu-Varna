package com.vale.warehouses.app.view_model;

import com.vale.warehouses.app.model.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class WarehouseVM {
    public Long id;

    public double width;

    public double length;

    public double height;

    public BigDecimal pricePerMonth;

    public WarehouseType type;
    
    public Category category;

    public Owner owner;

    public Set<SaleAgent> saleAgents = new HashSet<>();
    
    public Set<LeasingContract> leasingContracts = new HashSet<>();

    public String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<SaleAgent> getAgents() {
        return saleAgents;
    }

    public void setAgents(Set<SaleAgent> agents) {
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
