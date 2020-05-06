package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.app.model.SaleAgent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SaleAgentRepositoryTests extends BaseRepositoryTest {
    @Test
    public void whenFindById_thenReturnEmployee() {
        SaleAgent profile = new SaleAgent();
        profile.setAddress("some sale agent address");
        profile.setFirstName("sale agent's first name");
        profile.setLastName("sale agent's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());

        entityManager.persist(profile);

        SaleAgent fromDb = saleAgentRepository.findById(profile.getId()).orElse(null);

        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getUniqueCode()).isEqualTo(profile.getUniqueCode());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        SaleAgent fromDb = saleAgentRepository.findById(-11L).orElse(null);

        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfUsers_whenFindAll_thenReturnAllUsers() {
        List<SaleAgent> allEntities = saleAgentRepository.findAll();

        assertThat(allEntities)
                .hasSize(1)
                .extracting(SaleAgent::getUniqueCode)
                .isNotEmpty();
    }
}
