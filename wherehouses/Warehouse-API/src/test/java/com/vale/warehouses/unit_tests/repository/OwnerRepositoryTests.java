package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.app.model.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnerRepositoryTests extends BaseRepositoryTest {
    @Test
    public void whenFindById_thenReturnEmployee() {
        Owner profile = new Owner();
        profile.setAddress("some owner address");
        profile.setFirstName("owner's first name");
        profile.setLastName("owner's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());

        entityManager.persist(profile);

        Owner fromDb = ownerRepository.findById(profile.getId()).orElse(null);
        
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getUniqueCode()).isEqualTo(profile.getUniqueCode());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Owner fromDb = ownerRepository.findById(-11L).orElse(null);

        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfUsers_whenFindAll_thenReturnAllUsers() {
        List<Owner> allEntities = ownerRepository.findAll();

        assertThat(allEntities)
                .hasSize(1)
                .extracting(Owner::getUniqueCode)
                .isNotEmpty();
    }
}
