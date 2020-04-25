package com.vale.warehouses.app.model;

import com.fasterxml.jackson.annotation.*;
import com.vale.warehouses.auth.models.UserEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="owner")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner extends AbstractPerson {
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Warehouse> warehouses = new HashSet<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<LeasingContract> leasingContracts = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "relatedOwner",
            optional = true)
    @PrimaryKeyJoinColumn
    @JsonIgnore
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
