package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.repository.OwnerRepository;
import com.vale.warehouses.app.repository.SaleAgentRepository;
import com.vale.warehouses.app.repository.TenantRepository;
import com.vale.warehouses.app.service.AdminUserService;
import com.vale.warehouses.app.service.OwnerService;
import com.vale.warehouses.app.service.SaleAgentService;
import com.vale.warehouses.app.service.TenantService;
import com.vale.warehouses.app.service.interfaces.OwnerInterface;
import com.vale.warehouses.app.service.interfaces.SaleAgentInterface;
import com.vale.warehouses.app.service.interfaces.TenantInterface;
import com.vale.warehouses.app.service.interfaces.UserServiceInterface;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private UserServiceInterface userService;

    @MockBean
    private UserRepository repository;

    @MockBean
    private OwnerRepository ownerRepository;

    @MockBean
    private SaleAgentRepository saleAgentRepository;

    @MockBean
    private TenantRepository tenantRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OwnerInterface ownerService;

    @Autowired
    private SaleAgentInterface saleAgentService;

    @Autowired
    private TenantInterface tenantService;

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

    @Before
    public void setUp() {
        UserEntity user = new UserEntity();
        user.setUsername("alex");
        user.setId(1L);

        Mockito.when(repository.findByUsername(user.getUsername()))
                .thenReturn(user);

        Mockito.when(repository.findById(user.getId()))
                .thenReturn(java.util.Optional.of(user));
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "alex";
        UserEntity found = userService.getUser(1L);

        assertThat(found.getUsername()).isEqualTo(name);
    }
}
