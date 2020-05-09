package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.model.LeaseRequest;
import com.vale.warehouses.app.service.LeaseRequestService;
import com.vale.warehouses.app.service.TenantService;
import com.vale.warehouses.app.service.interfaces.LeaseRequestInterface;
import com.vale.warehouses.app.service.interfaces.TenantInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class LeaseRequestServiceTests extends BaseServiceTest {
    @Autowired
    protected LeaseRequestInterface leaseRequestService;

    @Autowired
    private TenantInterface tenantService;

    @TestConfiguration
    static class LeaseRequestServiceTestContextConfiguration {
        @Bean
        public LeaseRequestInterface leaseRequestService() {
            return new LeaseRequestService();
        }

        @Bean
        public TenantInterface tenantService() {
            return new TenantService();
        }
    }

    @Test
    public void whenValidId_thenLeaseRequestShouldBeFound() {
        Long id = 1L;
        LeaseRequest found = leaseRequestService.getLeaseRequest(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetAll_thenListOfLeaseRequestsShouldBeFound() {
        List<LeaseRequest> found = leaseRequestService.getLeaseRequests();

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenGetAllWithoutContractForTenant_thenListOfLeaseRequestsShouldBeFound() {
        LeaseRequest given = buildLeaseRequest();

        List<LeaseRequest> found = leaseRequestService.getLeaseRequestsWithoutContract(given.getTenant().getId());

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenGetAllWithoutContractWithoutTenant_thenListOfLeaseRequestsShouldBeFound() {
        List<LeaseRequest> found = leaseRequestService.getLeaseRequestsWithoutContract(null);

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenCreateNewLeaseRequest_thenLeaseRequestShouldBeFound() {
        LeaseRequest given = buildLeaseRequest();

        LeaseRequest found = leaseRequestService.createLeaseRequest(given);

        assertThat(found.getWarehouseType()).isEqualTo(given.getWarehouseType());
    }

    @Test
    public void whenUpdateLeaseRequest_thenLeaseRequestShouldBeFound() {
        LeaseRequest given = buildLeaseRequest();

        LeaseRequest found = leaseRequestService.updateLeaseRequest(given);

        assertThat(found.getWarehouseType()).isEqualTo(given.getWarehouseType());
    }
}
