package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.model.Warehouse;
import com.vale.warehouses.app.service.WarehouseService;
import com.vale.warehouses.app.service.interfaces.WarehouseInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class WarehouseServiceTests extends BaseServiceTest {
    @Autowired
    protected WarehouseInterface warehouseService;

    @TestConfiguration
    static class WarehouseServiceTestContextConfiguration {
        @Bean
        public WarehouseInterface warehouseService() {
            return new WarehouseService();
        }
    }

    @Test
    public void whenValidId_thenWarehouseShouldBeFound() {
        Long id = 1L;
        Warehouse found = warehouseService.getWarehouse(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetAll_thenListOfWarehousesShouldBeFound() {
        List<Warehouse> found = warehouseService.getWarehouses();

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenUpdateWarehouse_thenWarehouseShouldBeFound() {
        Warehouse given = buildWarehouse();

        Warehouse found = warehouseService.updateWarehouse(given);

        assertThat(found.getAddress()).isEqualTo(given.getAddress());
    }

    @Test
    public void whenValidOwnerIdGetWarehouses_thenWarehousesShouldBeFound() {
        Warehouse given = buildWarehouse();

        List<Warehouse> found = warehouseService
                .getWarehousesForOwner(given.getOwner().getId());

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getCategory()).isEqualTo(given.getCategory());
    }

    @Test
    public void whenValidSaleAgentIdGetWarehouses_thenWarehousesShouldBeFound() {
        Warehouse given = buildWarehouse();

        Long saleAgentId =  new ArrayList<>(given.getSaleAgents()).get(0).getId();
        List<Warehouse> found = warehouseService
                .getWarehousesForSaleAgent(saleAgentId);

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
        assertThat(found.get(0).getCategory()).isEqualTo(given.getCategory());
    }
}
