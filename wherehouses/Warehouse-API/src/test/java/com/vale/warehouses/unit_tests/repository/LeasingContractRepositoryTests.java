package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.app.model.LeasingContract;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeasingContractRepositoryTests extends BaseRepositoryTest {
    @Test
    public void whenFindByOwnerId_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();

        entityManager.persist(leasingContract);

        // when
        List<LeasingContract> found = leasingContractRepository
                .findByOwnerId(leasingContract.getOwner().getId());

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getOwner().getUniqueCode())
                .isEqualTo(leasingContract.getOwner().getUniqueCode());

        entityManager.clear();
    }

    @Test
    public void whenInvalidOwnerId_thenReturnNull() {
        List<LeasingContract> fromDb = leasingContractRepository.findByOwnerId(1533L);

        assertThat(fromDb).isEmpty();
    }
    
    @Test
    public void whenFindByOwnerIdAndLeasedTillAfter_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        entityManager.persist(leasingContract);

        // when
        List<LeasingContract> found = leasingContractRepository
                .findByOwnerIdAndLeasedTillAfter(
                        leasingContract.getOwner().getId(),
                        OffsetDateTime.now());

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getOwner().getUniqueCode())
                .isEqualTo(leasingContract.getOwner().getUniqueCode());

        entityManager.clear();
    }

    @Test
    public void whenInvalidOwnerIdAndLeasedTillAfter_thenReturnEmptyList() {
        List<LeasingContract> fromDb = leasingContractRepository
                .findByOwnerIdAndLeasedTillAfter(152636L, OffsetDateTime.now());

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenFindBySaleAgentIdAndLeasedTillAfter_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        entityManager.persist(leasingContract);

        // when
        List<LeasingContract> found = leasingContractRepository
                .findBySaleAgentIdAndLeasedTillAfter(
                        leasingContract.getSaleAgent().getId(),
                        OffsetDateTime.now());

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getOwner().getUniqueCode())
                .isEqualTo(leasingContract.getOwner().getUniqueCode());

        entityManager.clear();
    }

    @Test
    public void whenInvalidSaleAgentIdAndLeasedTillAfter_thenReturnEmptyList() {
        List<LeasingContract> fromDb = leasingContractRepository
                .findBySaleAgentIdAndLeasedTillAfter(
                        41564498L, OffsetDateTime.now());

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenFindByOwnerIdAndLeasedTillBetweenOrderByLeasedTillDesc_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        entityManager.persist(leasingContract);

        // when
        List<LeasingContract> found = leasingContractRepository
                .findByOwnerIdAndLeasedTillBetweenOrderByLeasedTillDesc(
                        leasingContract.getOwner().getId(),
                        OffsetDateTime.now(), OffsetDateTime.now().plusMonths(10));

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getOwner().getUniqueCode())
                .isEqualTo(leasingContract.getOwner().getUniqueCode());

        entityManager.clear();
    }

    @Test
    public void whenInvalidOwnerIdAndLeasedTillBetweenOrderByLeasedTillDesc_thenReturnEmptyList() {
        List<LeasingContract> fromDb = leasingContractRepository
                .findByOwnerIdAndLeasedTillBetweenOrderByLeasedTillDesc(
                        41564498L,
                        OffsetDateTime.now(),
                        OffsetDateTime.now().plusMonths(2));

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenFindBySaleAgentIdAndLeasedTillBetweenOrderByLeasedTillDesc_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        entityManager.persist(leasingContract);

        // when
        List<LeasingContract> found = leasingContractRepository
                .findBySaleAgentIdAndLeasedTillBetweenOrderByLeasedTillDesc(
                        leasingContract.getSaleAgent().getId(),
                        OffsetDateTime.now(),
                        OffsetDateTime.now().plusMonths(10));

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getOwner().getUniqueCode())
                .isEqualTo(leasingContract.getOwner().getUniqueCode());

        entityManager.clear();
    }

    @Test
    public void whenInvalidSaleAgentIdAndLeasedTillBetweenOrderByLeasedTillDesc_thenReturnEmptyList() {
        List<LeasingContract> fromDb = leasingContractRepository
                .findBySaleAgentIdAndLeasedTillBetweenOrderByLeasedTillDesc(
                        41564498L,
                        OffsetDateTime.now(),
                        OffsetDateTime.now().plusMonths(2));

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenFindBySaleAgentIdAndLeasedAtBetween_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        entityManager.persist(leasingContract);

        // when
        List<LeasingContract> found = leasingContractRepository
                .findBySaleAgentIdAndLeasedAtBetween(
                        leasingContract.getSaleAgent().getId(),
                        OffsetDateTime.now().minusDays(1),
                        OffsetDateTime.now().plusMonths(2));

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getOwner().getUniqueCode())
                .isEqualTo(leasingContract.getOwner().getUniqueCode());

        entityManager.clear();
    }

    @Test
    public void whenInvalidSaleAgentIdAndLeasedAtBetween_thenReturnEmptyList() {
        List<LeasingContract> fromDb = leasingContractRepository
                .findBySaleAgentIdAndLeasedAtBetween(
                        41564498L,
                        OffsetDateTime.now().minusDays(1),
                        OffsetDateTime.now().plusMonths(2));

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenBySaleAgentId_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        entityManager.persist(leasingContract);

        // when
        List<LeasingContract> found = leasingContractRepository
                .findBySaleAgentId(leasingContract.getSaleAgent().getId());

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getOwner().getUniqueCode())
                .isEqualTo(leasingContract.getOwner().getUniqueCode());

        entityManager.clear();
    }

    @Test
    public void whenInvalidSaleAgentId_thenReturnEmptyList() {
        List<LeasingContract> fromDb = leasingContractRepository
                .findBySaleAgentId(41564498L);

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenByWarehouseIdInOrderByLeasedAtDesc_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        entityManager.persist(leasingContract);

        List<Long> ids = Collections.singletonList(
                leasingContract.getWarehouse().getId());

        // when
        List<LeasingContract> found = leasingContractRepository
                .findByWarehouseIdInOrderByLeasedAtDesc(new HashSet<>(ids));

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getWarehouse().getAddress())
                .isEqualTo(leasingContract.getWarehouse().getAddress());

        entityManager.clear();
    }

    @Test
    public void whenInvalidWarehouseIdInOrderByLeasedAtDesc_thenReturnEmptyList() {
        List<LeasingContract> fromDb = leasingContractRepository
                .findByWarehouseIdInOrderByLeasedAtDesc(new HashSet<>(Collections.singletonList(41564498L)));

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenByWarehouseIdInAndLeasedTillAfterAndLeasedAtBeforeOrderByLeasedAtDesc_thenReturnLeasingContractList() {
        // given
        createWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        entityManager.persist(leasingContract);

        List<Long> ids = Collections.singletonList(
                leasingContract.getWarehouse().getId());

        // when
        List<LeasingContract> found = leasingContractRepository
                .findByWarehouseIdInAndLeasedTillAfterAndLeasedAtBeforeOrderByLeasedAtDesc(
                        new HashSet<>(ids),
                        OffsetDateTime.now().minusDays(1),
                        OffsetDateTime.now().plusMonths(10));

        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getWarehouse().getAddress())
                .isEqualTo(leasingContract.getWarehouse().getAddress());

        entityManager.clear();
    }

    @Test
    public void whenInvalidWarehouseIdInAndLeasedTillAfterAndLeasedAtBeforeOrderByLeasedAtDesc_thenReturnEmptyList() {
        List<LeasingContract> fromDb = leasingContractRepository
                .findByWarehouseIdInAndLeasedTillAfterAndLeasedAtBeforeOrderByLeasedAtDesc(
                        new HashSet<>(Collections.singletonList(41564498L)),
                        OffsetDateTime.now().minusDays(1),
                        OffsetDateTime.now().plusMonths(2));

        assertThat(fromDb).isEmpty();
    }
}
