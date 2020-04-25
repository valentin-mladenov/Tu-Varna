package com.vale.warehouses.app.service;

import com.vale.warehouses.app.model.LeaseRequest;
import com.vale.warehouses.app.repository.LeaseRequestRepository;
import com.vale.warehouses.app.service.interfaces.LeaseRequestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LeaseRequestService implements LeaseRequestInterface {
    @Autowired
    private LeaseRequestRepository leaseRequestRepository;

    @Override
    public List<LeaseRequest> getLeaseRequests()
    {
        List<LeaseRequest> leaseRequests = leaseRequestRepository.findAll();

        if(leaseRequests.size() > 0) {
            return leaseRequests;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public LeaseRequest getLeaseRequest(Long id) throws NullPointerException
    {
        Optional<LeaseRequest> leaseRequest = leaseRequestRepository.findById(id);

        if(!leaseRequest.isPresent()) {
            throw new NullPointerException("No record exist for given id");
        }

        return leaseRequest.get();
    }

    @Override
    public LeaseRequest createLeaseRequest(LeaseRequest entity)
    {
        if(!Objects.isNull(entity.getId()) && leaseRequestRepository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("Record with that ID already exists");
        }

        return leaseRequestRepository.save(entity);
    }

    @Override
    public LeaseRequest updateLeaseRequest(LeaseRequest entity) throws NullPointerException
    {
        Optional<LeaseRequest> leaseRequest = leaseRequestRepository.findById(entity.getId());

        if(!leaseRequest.isPresent())
        {
            throw new NullPointerException("No record with that ID");
        }

        return leaseRequestRepository.save(entity);
    }

    @Override
    public void deleteLeaseRequest(Long id) throws NullPointerException
    {
        Optional<LeaseRequest> leaseRequest = leaseRequestRepository.findById(id);

        if(!leaseRequest.isPresent())
        {
            throw new NullPointerException("No record exist for given id");
        }

        leaseRequestRepository.deleteById(id);
    }
}
