package com.vale.warehouses.service.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Warehouse implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("width")
    private double width;

    @SerializedName("length")
    private double length;

    @SerializedName("height")
    private double height;

    @SerializedName("pricePerMonth")
    private BigDecimal pricePerMonth;

    @SerializedName("type")
    private WarehouseType type;

    @SerializedName("address")
    private String address;

    @SerializedName("category")
    private Category category;

    @SerializedName("owner")
    private Owner owner;

    @SerializedName("saleAgents")
    private Set<SaleAgent> saleAgents = new HashSet<>();

    @SerializedName("leasingContracts")
    private Set<LeasingContract> leasingContracts = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public WarehouseType getType() {
        return type;
    }

    public void setType(WarehouseType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
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

    public BigDecimal getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(BigDecimal pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public double getArea() {
        return getWidth() * getLength();
    }

    public double getVolume() {
        return getArea() * getHeight();
    }

    @NonNull
    @Override
    public String toString()
    {
        return( address + " (" + pricePerMonth + ")");
    }
}
