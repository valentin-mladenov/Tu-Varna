package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.app.model.LeaseRequest;

import java.util.List;

public interface LeaseRequestInterface {
    List<LeaseRequest> getLeaseRequests();
    List<LeaseRequest> getLeaseRequestsWithoutContract(Long id);

    LeaseRequest getLeaseRequest(Long id) throws NullPointerException;

    LeaseRequest createLeaseRequest(LeaseRequest entity);

    LeaseRequest updateLeaseRequest(LeaseRequest entity) throws NullPointerException;

    void deleteLeaseRequest(Long id);
}
