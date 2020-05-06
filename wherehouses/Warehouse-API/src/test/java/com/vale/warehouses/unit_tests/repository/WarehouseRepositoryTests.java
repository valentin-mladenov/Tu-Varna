package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.app.model.Warehouse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WarehouseRepositoryTests extends BaseRepositoryTest {
    @Test
    public void whenFindByOwnerId_thenReturnWarehouseList() {
        // given
        Warehouse warehouse = buildWarehouse();

        entityManager.persist(warehouse);

        // when
        List<Warehouse> found = warehouseRepository.findByOwnerId(warehouse.getOwner().getId());

        // then
        assertThat(found.get(0).getAddress()).isEqualTo(warehouse.getAddress());

        entityManager.clear();
    }

    @Test
    public void whenInvalidOwnerId_thenReturnEmptyList() {
        List<Warehouse> fromDb = warehouseRepository.findByOwnerId(1533L);

        assertThat(fromDb).isEmpty();
    }
    
    @Test
    public void whenFindBySaleAgentId_thenReturnWarehouseList() {
        // given
        Warehouse warehouse = buildWarehouse();

        entityManager.persist(warehouse);

        Long id = new ArrayList<>(warehouse.getSaleAgents()).get(0).getId();

        // when
        List<Warehouse> found = warehouseRepository.findBySaleAgentsId(id);

        // then
        assertThat(found.get(0).getAddress()).isEqualTo(warehouse.getAddress());
        entityManager.clear();
    }

    @Test
    public void whenInvalidSaleAgentId_thenReturnEmptyList() {
        List<Warehouse> fromDb = warehouseRepository.findBySaleAgentsId(13311L);

        assertThat(fromDb).isEmpty();
    }

    @Test
    public void whenFindById_thenReturnWarehouse() {
        // given
        Warehouse warehouse = buildWarehouse();

        entityManager.persist(warehouse);

        // when
        Warehouse found = warehouseRepository.findById(warehouse.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getAddress()).isEqualTo(warehouse.getAddress());
        entityManager.clear();
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Warehouse fromDb = warehouseRepository.findById(13311L).orElse(null);

        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfWarehouses_whenFindAll_thenReturnAllWarehouses() {
        Warehouse warehouse1 = buildWarehouse();
        Warehouse warehouse2 = buildWarehouse();

        entityManager.persist(warehouse1);
        entityManager.persist(warehouse2);

        List<Warehouse> allEntities = warehouseRepository.findAll();

        assertThat(allEntities)
                .hasSize(2)
                .extracting(Warehouse::getAddress)
                .isNotEmpty();
        entityManager.clear();
    }
}
