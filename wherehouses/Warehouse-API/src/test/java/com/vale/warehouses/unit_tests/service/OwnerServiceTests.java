package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.model.Owner;
import com.vale.warehouses.app.service.OwnerService;
import com.vale.warehouses.app.service.interfaces.OwnerInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class OwnerServiceTests extends BaseServiceTest {
    @Autowired
    protected OwnerInterface ownerService;

    @TestConfiguration
    static class OwnerServiceTestContextConfiguration {
        @Bean
        public OwnerInterface ownerService() {
            return new OwnerService();
        }
    }

    @Test
    public void whenValidId_thenOwnerShouldBeFound() {
        Long id = 1L;
        Owner found = ownerService.getOwner(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenGetAll_thenListOfOwnersShouldBeFound() {
        List<Owner> found = ownerService.getOwners();

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenCreateNewOwner_thenOwnerShouldBeFound() {
        Owner given = buildOwner();

        Owner found = ownerService.createOwner(given);

        assertThat(found.getAddress()).isEqualTo(given.getAddress());
    }

    @Test
    public void whenUpdateOwner_thenOwnerShouldBeFound() {
        Owner given = buildOwner();

        Owner found = ownerService.updateOwner(given);

        assertThat(found.getAddress()).isEqualTo(given.getAddress());
    }
}
