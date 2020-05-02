package com.vale.warehouses.app.service;

import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.repository.LeasingContractRepository;
import com.vale.warehouses.app.service.interfaces.LeasingContractInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LeasingContractService implements LeasingContractInterface {
    @Autowired
    private LeasingContractRepository repository;

    @Override
    public List<LeasingContract> getLeasingContracts()
    {
        List<LeasingContract> leasingContracts = repository.findAll();

        if(leasingContracts.size() > 0) {
            return leasingContracts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<LeasingContract> getLeasingContractsForOwner(Long id) {
        List<LeasingContract> leasingContracts = repository.findByOwnerId(id);

        if(leasingContracts.size() > 0) {
            return leasingContracts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<LeasingContract> getLeasingContractsForWarehouse(Long id) {
        List<LeasingContract> leasingContracts = repository.findByWarehouseIdOrderByLeasedAtDesc(id);

        if(leasingContracts.size() > 0) {
            return leasingContracts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<LeasingContract> getCurrentlyActiveLeasingContractsForOwner(
            Long id, OffsetDateTime leasedTill
    ) {
        List<LeasingContract> leasingContracts = repository
                .findByOwnerIdAndLeasedTillAfter(id, leasedTill);

        if(leasingContracts.size() > 0) {
            return leasingContracts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<LeasingContract> getCurrentlyActiveLeasingContractsForSaleAgent(
            Long id, OffsetDateTime leasedTill
    ) {
        List<LeasingContract> leasingContracts = repository
                .findBySaleAgentIdAndLeasedTillAfter(id, leasedTill);

        if(leasingContracts.size() > 0) {
            return leasingContracts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<LeasingContract> getLeasingContractsForSaleAgent(
            Long id, OffsetDateTime fromDate, OffsetDateTime toDate) {
        List<LeasingContract> leasingContracts = repository
                .findBySaleAgentIdAndLeasedAtBetween(id, fromDate, toDate);

        if(leasingContracts.size() > 0) {
            return leasingContracts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<LeasingContract> getEndingSoonLeasingContractsForSaleAgent(
            Long id, OffsetDateTime leasedTill
    ) {
        List<LeasingContract> leasingContracts = repository
                .findBySaleAgentIdAndLeasedTillBeforeOrderByLeasedTillDesc(id, leasedTill);

        if(leasingContracts.size() > 0) {
            return leasingContracts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<LeasingContract> getEndingSoonLeasingContractsForOwner(Long id, OffsetDateTime leasedTill) {
        List<LeasingContract> leasingContracts = repository
                .findByOwnerIdAndLeasedTillBeforeOrderByLeasedTillDesc(id, leasedTill);

        if(leasingContracts.size() > 0) {
            return leasingContracts;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public LeasingContract getLeasingContract(Long id) throws NullPointerException
    {
        Optional<LeasingContract> leasingContract = repository.findById(id);

        if(!leasingContract.isPresent()) {
            throw new NullPointerException("No record exist for given id");
        }

        return leasingContract.get();
    }

    @Override
    public LeasingContract createLeasingContract(LeasingContract entity)
    {
        if(!Objects.isNull(entity.getId()) && repository.existsById(entity.getId()))
        {
            throw new IllegalArgumentException("Record with that ID already exists");
        }

        return repository.save(entity);
    }

    @Override
    public LeasingContract updateLeasingContract(LeasingContract entity) throws NullPointerException
    {
        Optional<LeasingContract> leasingContract = repository.findById(entity.getId());

        if(!leasingContract.isPresent())
        {
            throw new NullPointerException("No record with that ID");
        }

        return repository.save(entity);
    }

    @Override
    public void deleteLeasingContract(Long id) throws NullPointerException
    {
        Optional<LeasingContract> leasingContract = repository.findById(id);

        if(!leasingContract.isPresent())
        {
            throw new NullPointerException("No record exist for given id");
        }

        repository.deleteById(id);
    }
}
