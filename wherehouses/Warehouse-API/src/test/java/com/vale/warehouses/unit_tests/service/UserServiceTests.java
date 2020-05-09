package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.service.AdminUserService;
import com.vale.warehouses.app.service.OwnerService;
import com.vale.warehouses.app.service.SaleAgentService;
import com.vale.warehouses.app.service.TenantService;
import com.vale.warehouses.app.service.interfaces.OwnerInterface;
import com.vale.warehouses.app.service.interfaces.SaleAgentInterface;
import com.vale.warehouses.app.service.interfaces.TenantInterface;
import com.vale.warehouses.app.service.interfaces.UserServiceInterface;
import com.vale.warehouses.auth.models.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceTests extends BaseServiceTest {
    @Autowired
    protected UserServiceInterface userService;

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public UserServiceInterface userService() {
            return new AdminUserService();
        }

        @Bean
        public OwnerInterface ownerService() {
            return new OwnerService();
        }

        @Bean
        public TenantInterface tenantService() {
            return new TenantService();
        }

        @Bean
        public SaleAgentInterface saleAgentService() {
            return new SaleAgentService();
        }

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Test
    public void whenValidId_thenUserShouldBeFound() {
        Long id = 1L;
        UserEntity found = userService.getUser(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    @Test
    public void whenValidName_thenUsersShouldBeFound() {
        String name = "admin";
        UserEntity found = userService.findByUsername(name);

        assertThat(found.getUsername()).isEqualTo(name);
    }

    @Test
    public void whenGetAll_thenListOfUsersShouldBeFound() {
        List<UserEntity> found = userService.getUsers();

        assertThat(found).isNotEmpty();
        assertThat(found.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    public void whenCreateNewUser_thenUserShouldBeFound() {
        UserEntity given = buildUser();

        given.setPassword("123456");
        given.setPasswordConfirm("123456");

        UserEntity found = userService.createUser(given);

        assertThat(found.getUsername()).isEqualTo(given.getUsername());
    }

    @Test
    public void whenUpdateUser_thenUserShouldBeFound() {
        UserEntity given = buildUser();

        UserEntity found = userService.updateUser(given.getId(), given);

        assertThat(found.getUsername()).isEqualTo(given.getUsername());
    }
}
