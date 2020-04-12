package com.vale.warehouses.app.service;

import com.vale.warehouses.app.model.LeaseRequest;
import com.vale.warehouses.app.repository.LeaseRequestRepository;
import com.vale.warehouses.app.service.interfaces.LeaseRequestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaseRequestService implements LeaseRequestInterface {
    @Autowired
    private LeaseRequestRepository leaseRequestRepository;

    @Override
    public List<LeaseRequest> getLeaseRequests()
    {
        List<LeaseRequest> employeeList = leaseRequestRepository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public LeaseRequest getLeaseRequest(Long id) throws NullPointerException
    {
        Optional<LeaseRequest> user = leaseRequestRepository.findById(id);

        if(!user.isPresent()) {
            throw new NullPointerException("No record exist for given id");
        }

        return user.get();
    }

    @Override
    public LeaseRequest createLeaseRequest(LeaseRequest entity)
    {
        if(leaseRequestRepository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("Record with that ID already exists");
        }

        return leaseRequestRepository.save(entity);
    }

    @Override
    public LeaseRequest updateLeaseRequest(LeaseRequest entity) throws NullPointerException
    {
        Optional<LeaseRequest> user = leaseRequestRepository.findById(entity.getId());

        if(!user.isPresent())
        {
            throw new NullPointerException("No record with that ID");
        }

        return leaseRequestRepository.save(entity);
    }

    @Override
    public void deleteLeaseRequest(Long id) throws NullPointerException
    {
        Optional<LeaseRequest> user = leaseRequestRepository.findById(id);

        if(!user.isPresent())
        {
            throw new NullPointerException("No record exist for given id");
        }

        leaseRequestRepository.deleteById(id);
    }
}
