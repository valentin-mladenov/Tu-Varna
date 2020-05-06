package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.auth.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private UserRepository repository;

    @Test
    public void whenFindByName_thenReturnUser() {
//        // given
//        UserEntity user = new UserEntity();
//
//        user.setUsername("alex");
//
//        entityManager.persistAndFlush(user);
//
//        // when
//        UserEntity found = repository.findByUsername(user.getUsername());
//
//        // then
//        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

}
