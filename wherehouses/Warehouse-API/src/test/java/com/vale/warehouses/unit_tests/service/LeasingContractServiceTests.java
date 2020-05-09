package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.app.service.LeasingContractService;
import com.vale.warehouses.app.service.interfaces.LeasingContractInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class LeasingContractServiceTests extends BaseServiceTest {
    @Autowired
    protected LeasingContractInterface leasingContractService;

    @TestConfiguration
    static class LeasingContractServiceTestContextConfiguration {
        @Bean
        public LeasingContractInterface leasingContractService() {
            return new LeasingContractService();
        }
    }

    @Test
    public void whenValidId_thenLeasingContractShouldBeFound() {
        Long id = 1L;
        LeasingContract found = leasingContractService.getLeasingContract(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetAll_thenListOfLeasingContractsShouldBeFound() {
        List<LeasingContract> found = leasingContractService.getLeasingContracts();

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenCreateNewLeasingContract_thenLeasingContractShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        LeasingContract found = leasingContractService.createLeasingContract(given);

        assertThat(found.getLeasedTill()).isEqualTo(given.getLeasedTill());
        assertThat(found.getLeasedAt()).isEqualTo(given.getLeasedAt());
    }

    @Test
    public void whenUpdateLeasingContract_thenLeasingContractShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        LeasingContract found = leasingContractService.updateLeasingContract(given);

        assertThat(found.getLeasedTill()).isEqualTo(given.getLeasedTill());
        assertThat(found.getLeasedAt()).isEqualTo(given.getLeasedAt());
    }

    @Test
    public void whenValidWarehouseId_thenLeasingContractsShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        List<LeasingContract> found = leasingContractService.getLeasingContractsForWarehouse(
                new HashSet<>(Collections.singletonList(given.getWarehouse().getId())), null, null);

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getLeasedAt()).isEqualTo(given.getLeasedAt());
        assertThat(found.get(0).getLeasedTill()).isEqualTo(given.getLeasedTill());
    }

    @Test
    public void whenValidWarehouseIdAndDates_thenLeasingContractsShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        List<LeasingContract> found = leasingContractService.getLeasingContractsForWarehouse(
                new HashSet<>(Collections.singletonList(given.getWarehouse().getId())),
                nowDateTime.minusDays(1),
                nowDateTime.plusMonths(1));

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getLeasedAt()).isEqualTo(given.getLeasedAt());
        assertThat(found.get(0).getLeasedTill()).isEqualTo(given.getLeasedTill());
    }

    @Test
    public void whenValidOwnerId_thenLeasingContractsShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        List<LeasingContract> found = leasingContractService
                .getLeasingContractsForOwner(given.getOwner().getId());

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getLeasedAt()).isEqualTo(given.getLeasedAt());
        assertThat(found.get(0).getLeasedTill()).isEqualTo(given.getLeasedTill());
    }

    @Test
    public void whenValidOwnerIdAndLeasedTillDate_thenLeasingContractsShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        List<LeasingContract> found = leasingContractService
                .getCurrentlyActiveLeasingContractsForOwner(
                        given.getOwner().getId(), nowDateTime.minusDays(1));

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getLeasedAt()).isEqualTo(given.getLeasedAt());
        assertThat(found.get(0).getLeasedTill()).isEqualTo(given.getLeasedTill());
    }

    @Test
    public void whenValidOwnerIdAndLeasedDates_thenLeasingContractsShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        List<LeasingContract> found = leasingContractService
                .getEndingSoonLeasingContractsForOwner(
                        given.getOwner().getId(),
                        nowDateTime.minusDays(1),
                        nowDateTime.plusMonths(1));

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getLeasedAt()).isEqualTo(given.getLeasedAt());
        assertThat(found.get(0).getLeasedTill()).isEqualTo(given.getLeasedTill());
    }

    @Test
    public void whenValidSaleAgentIdAndBetweenDates_thenLeasingContractsShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        List<LeasingContract> found = leasingContractService
                .getLeasingContractsForSaleAgent(
                        given.getSaleAgent().getId(),
                        nowDateTime.minusDays(1),
                        nowDateTime.plusMonths(1));

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getLeasedAt()).isEqualTo(given.getLeasedAt());
        assertThat(found.get(0).getLeasedTill()).isEqualTo(given.getLeasedTill());
    }

    @Test
    public void whenValidSaleAgentIdAndTillDate_thenLeasingContractsShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        List<LeasingContract> found = leasingContractService
                .getCurrentlyActiveLeasingContractsForSaleAgent(
                        given.getSaleAgent().getId(),
                        nowDateTime.minusDays(1));

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getLeasedAt()).isEqualTo(given.getLeasedAt());
        assertThat(found.get(0).getLeasedTill()).isEqualTo(given.getLeasedTill());
    }

    @Test
    public void whenValidSaleAgentIdAndLeasedDates_thenLeasingContractsShouldBeFound() {
        LeasingContract given = buildLeasingContract();

        List<LeasingContract> found = leasingContractService
                .getEndingSoonLeasingContractsForSaleAgent(
                        given.getSaleAgent().getId(),
                        nowDateTime.minusDays(1),
                        nowDateTime.plusMonths(1));

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getLeasedAt()).isEqualTo(given.getLeasedAt());
        assertThat(found.get(0).getLeasedTill()).isEqualTo(given.getLeasedTill());
    }
}
