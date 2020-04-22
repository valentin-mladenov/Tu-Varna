package com.vale.warehouses.service.model;

public enum RoleType {
    Admin(1),
    Owner(2),
    SaleAgent(3),
    Tenant(4);

    private final int value;
    private RoleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
