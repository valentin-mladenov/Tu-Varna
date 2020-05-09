package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.app.model.Tenant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TenantRepositoryTests extends BaseRepositoryTest {
    @Test
    public void whenFindById_thenReturnEmployee() {
        Tenant profile = new Tenant();
        profile.setAddress("some tenant address");
        profile.setFirstName("tenant's first name");
        profile.setLastName("tenant's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());

        entityManager.persist(profile);

        Tenant fromDb = tenantRepository.findById(profile.getId()).orElse(null);

        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getUniqueCode()).isEqualTo(profile.getUniqueCode());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Tenant fromDb = tenantRepository.findById(-11L).orElse(null);

        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfUsers_whenFindAll_thenReturnAllUsers() {
        List<Tenant> allEntities = tenantRepository.findAll();

        assertThat(allEntities)
                .hasSize(1)
                .extracting(Tenant::getUniqueCode)
                .isNotEmpty();
    }
}
