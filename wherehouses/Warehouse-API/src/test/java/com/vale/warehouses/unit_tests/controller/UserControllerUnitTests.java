package com.vale.warehouses.unit_tests.controller;

import com.vale.warehouses.app.controller.AdminUserController;
import com.vale.warehouses.app.service.interfaces.UserServiceInterface;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminUserController.class)
public class UserControllerUnitTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceInterface userService;

    @MockBean
    private UserRepository userRepository;

    @TestConfiguration
    static class UserControllerIntegrationTestContextConfiguration {
//        @Bean
//        public UserServiceInterface userService() {
//            return new AdminUserService();
//        }
    }

    @Before
    public void setUp() {
//        UserEntity user = new UserEntity();
//        user.setUsername("alex");
//        user.setId(1L);
//
//        Mockito.when(repository.findByUsername(user.getUsername()))
//                .thenReturn(user);
//
//        Mockito.when(repository.findById(user.getId()))
//                .thenReturn(java.util.Optional.of(user));
    }

    @Test
    public void whenPostEmployee_thenCreateEmployee()
            throws Exception {

        UserEntity alex = new UserEntity();
        alex.setId(1L);

        List<UserEntity> allUsers = Collections.singletonList(alex);

        given(userService.getUsers()).willReturn(allUsers);

        mvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is(alex.getUsername())));
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
        UserEntity alex = new UserEntity();
        alex.setUsername("alex");

        UserEntity john = new UserEntity();
        john.setUsername("john");

        UserEntity bob = new UserEntity();
        bob.setUsername("bob");

        List<UserEntity> allEmployees = Arrays.asList(alex, john, bob);

        given(userService.getUsers()).willReturn(allEmployees);

        mvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].username", is(alex.getUsername())))
                .andExpect(jsonPath("$[1].username", is(john.getUsername())))
                .andExpect(jsonPath("$[2].username", is(bob.getUsername())));

        verify(userService, VerificationModeFactory.times(1)).getUsers();
        reset(userService);
    }
}
