package com.vale.warehouses.app.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="tenant")
public class Tenant extends AbstractPerson {
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<LeaseRequest> leaseRequests;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<LeasingContract> leasingContracts;

    public Set<LeaseRequest> getLeaseRequests() {
        return leaseRequests;
    }

    public void setLeaseRequests(Set<LeaseRequest> leaseRequests) {
        this.leaseRequests = leaseRequests;
    }

    public Set<LeasingContract> getLeasingContracts() {
        return leasingContracts;
    }

    public void setLeasingContracts(Set<LeasingContract> leasingContracts) {
        this.leasingContracts = leasingContracts;
    }
}
