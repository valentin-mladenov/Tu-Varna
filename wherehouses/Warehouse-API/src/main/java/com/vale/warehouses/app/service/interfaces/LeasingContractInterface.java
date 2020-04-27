package com.vale.warehouses.app.service.interfaces;

import com.vale.warehouses.app.model.LeasingContract;

import java.time.OffsetDateTime;
import java.util.List;

public interface LeasingContractInterface {
    List<LeasingContract> getLeasingContracts();

    List<LeasingContract> getLeasingContractsForOwner(Long id);

    List<LeasingContract> getLeasingContractsForSaleAgent(Long id, OffsetDateTime fromDate, OffsetDateTime toDate);

    List<LeasingContract> getCurrentlyActiveLeasingContractsForOwner(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> getCurrentlyActiveLeasingContractsForSaleAgent(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> getEndingSoonLeasingContractsForSaleAgent(Long id, OffsetDateTime leasedTill);

    List<LeasingContract> getEndingSoonLeasingContractsForOwner(Long id, OffsetDateTime leasedTill);

    LeasingContract getLeasingContract(Long id) throws NullPointerException;

    LeasingContract createLeasingContract(LeasingContract entity);

    LeasingContract updateLeasingContract(LeasingContract entity) throws NullPointerException;

    void deleteLeasingContract(Long id);
}
