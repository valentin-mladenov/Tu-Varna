package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.auth.models.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests extends BaseRepositoryTest {
    @Test
    public void whenFindByName_thenReturnUser() {
        // given
        UserEntity user = new UserEntity();

        user.setUsername("alex");
        user.setEmail("admin@admin.admin");

        entityManager.persist(user);

        // when
        UserEntity found = userRepository.findByUsername(user.getUsername());

        // then
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        UserEntity fromDb = userRepository.findByUsername("doesNotExist");

        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindById_thenReturnUser() {
        UserEntity user = new UserEntity();

        user.setUsername("alex");
        user.setEmail("admin@admin.admin");

        entityManager.persist(user);

        UserEntity fromDb = userRepository.findById(user.getId()).orElse(null);
        assertThat(fromDb.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        UserEntity fromDb = userRepository.findById(-11L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfUsers_whenFindAll_thenReturnAllUsers() {
        baseRolesAndUsersSetup();
        entityManager.flush();

        List<UserEntity> allEmployees = userRepository.findAll();

        assertThat(allEmployees).hasSize(4).extracting(UserEntity::getUsername)
                .containsExactlyInAnyOrder("admin", "tenant", "saleagent", "owner");
    }
}
