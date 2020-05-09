package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.model.SaleAgent;
import com.vale.warehouses.app.service.SaleAgentService;
import com.vale.warehouses.app.service.interfaces.SaleAgentInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class SaleAgentServiceTests extends BaseServiceTest {
    @Autowired
    protected SaleAgentInterface saleAgentService;

    @TestConfiguration
    static class SaleAgentServiceTestContextConfiguration {
        @Bean
        public SaleAgentInterface saleAgentService() {
            return new SaleAgentService();
        }
    }

    @Test
    public void whenValidId_thenSaleAgentShouldBeFound() {
        Long id = 1L;
        SaleAgent found = saleAgentService.getSaleAgent(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetAll_thenListOfSaleAgentsShouldBeFound() {
        List<SaleAgent> found = saleAgentService.getSaleAgents();

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenCreateNewSaleAgent_thenSaleAgentShouldBeFound() {
        SaleAgent given = buildSaleAgent();

        SaleAgent found = saleAgentService.createSaleAgent(given);

        assertThat(found.getAddress()).isEqualTo(given.getAddress());
    }

    @Test
    public void whenUpdateSaleAgent_thenSaleAgentShouldBeFound() {
        SaleAgent given = buildSaleAgent();

        SaleAgent found = saleAgentService.updateSaleAgent(given);

        assertThat(found.getAddress()).isEqualTo(given.getAddress());
    }
}
