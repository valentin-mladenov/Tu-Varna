package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.model.Tenant;
import com.vale.warehouses.app.service.TenantService;
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
public class TenantServiceTests extends BaseServiceTest {
    @Autowired
    protected TenantInterface tenantService;

    @TestConfiguration
    static class TenantServiceTestContextConfiguration {
        @Bean
        public TenantInterface tenantService() {
            return new TenantService();
        }
    }

    @Test
    public void whenValidId_thenTenantShouldBeFound() {
        Long id = 1L;
        Tenant found = tenantService.getTenant(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetAll_thenListOfTenantsShouldBeFound() {
        List<Tenant> found = tenantService.getTenants();

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenCreateNewTenant_thenTenantShouldBeFound() {
        Tenant given = buildTenant();

        Tenant found = tenantService.createTenant(given);

        assertThat(found.getAddress()).isEqualTo(given.getAddress());
    }

    @Test
    public void whenUpdateTenant_thenTenantShouldBeFound() {
        Tenant given = buildTenant();

        Tenant found = tenantService.updateTenant(given);

        assertThat(found.getAddress()).isEqualTo(given.getAddress());
    }
}
