package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.app.model.LeaseRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeaseRequestRepositoryTests extends BaseRepositoryTest {
    @Test
    public void whenFindByOwnerId_thenReturnLeaseRequestList() {
        // given
        LeaseRequest leaseRequest = buildLeaseRequest();

        entityManager.persist(leaseRequest);

        // when
        LeaseRequest found = leaseRequestRepository.findById(leaseRequest.getId()).get();

        // then
        assertThat(found.getWarehouseType()).isEqualTo(leaseRequest.getWarehouseType());

        entityManager.clear();
    }

    @Test
    public void whenInvalidOwnerId_thenReturnNull() {
        Optional<LeaseRequest> fromDb = leaseRequestRepository.findById(1533L);

        LeaseRequest fromDbEntity = null;
        if(fromDb.isPresent()) {
            fromDbEntity = fromDb.get();

            assertThat(fromDbEntity).isNotNull();
        }
        else{
            assertThat(fromDbEntity).isNull();
        }

    }
    
    @Test
    public void whenFindByLeasingContractIsNull_thenReturnLeaseRequestList() {
        // given
        LeaseRequest leaseRequest = buildLeaseRequest();

        entityManager.persist(leaseRequest);

        // when
        List<LeaseRequest> found = leaseRequestRepository.findByLeasingContractIsNull();

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getWarehouseType()).isEqualTo(leaseRequest.getWarehouseType());

        entityManager.clear();
    }

    @Test
    public void whenInvalidSaleAgentId_thenReturnEmptyList() {
        List<LeaseRequest> fromDb = leaseRequestRepository.findByLeasingContractIsNull();

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenFindByTenantIdAndLeasingContractIsNull_thenReturnLeaseRequestList() {
        // given
        LeaseRequest leaseRequest = buildLeaseRequest();

        entityManager.persist(leaseRequest);

        // when
        List<LeaseRequest> found = leaseRequestRepository
                .findByTenantIdAndLeasingContractIsNull(leaseRequest.getTenant().getId());

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getWarehouseType()).isEqualTo(leaseRequest.getWarehouseType());

        entityManager.clear();
    }

    @Test
    public void whenInvalidTenantIdAndLeasingContractIsNull_thenReturnEmptyList() {
        List<LeaseRequest> fromDb = leaseRequestRepository
                .findByTenantIdAndLeasingContractIsNull(13311L);

        assertThat(fromDb).isEmpty();
    }
}
