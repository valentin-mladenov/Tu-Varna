package com.vale.warehouses.integration_tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.vale.warehouses.Start;
import com.vale.warehouses.auth.models.RoleEntity;
import com.vale.warehouses.auth.models.TokenEntity;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.RoleRepository;
import com.vale.warehouses.auth.repository.TokenRepository;
import com.vale.warehouses.auth.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes= Start.class)

public class UserControllerIntegrationTest extends BaseIntegrationTest {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @After
    public void resetDb() {
//        tokenRepository.deleteAll();
//
//        List<UserEntity> a = repository.findAll();
//        a.forEach(ala -> ala.setRoles(new HashSet<>()));
//        repository.saveAll(a);
//        repository.saveAndFlush(a1.get(0));
//
//        repository.deleteAll();
//        roleRepository.deleteAll();
//        List<UserEntity> a1 = repository.findAll();
//        List<RoleEntity> r1 = roleRepository.findAll();
    }

    @Before
    public void setUp() {
        List<UserEntity> a = repository.findAll();

        if(a.size() > 0) {
            return;
        }

        RoleEntity role = new RoleEntity();
        role.setId(1L);
        role.setName("Admin");

        roleRepository.saveAndFlush(role);

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);

        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword("$2a$10$1/Z488jcNgLExoA7P894n.uR7jQcFcissKqDzPEK0QK7gz30i.Cdm");
        user.setEmail("admin@admin.admin");
        user.setRoles(roles);

        repository.saveAndFlush(user);
    }


    @Test
    public void givenUsers_whenGetUsers_thenStatus200() throws Exception {
        createTestUser("bob");
        createTestUser("alex");

        TokenEntity token = this.adminLogin();

        ResultActions result = mvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<UserEntity>>(){}.getType();
        List<UserEntity> userEntities = buildGson().fromJson(resultString, listType);

        List<UserEntity> userDbEntities = repository.findAll();


        assertThat(userEntities).isNotNull();
        assertThat(userEntities).hasSize(userDbEntities.size());
    }

    @Test
    public void givenUsers_whenGetUser_thenStatus200() throws Exception {
        TokenEntity token = this.adminLogin();

        ResultActions result = mvc.perform(get("/api/user/1").contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", is("admin")));

        String resultString = result.andReturn().getResponse().getContentAsString();

        UserEntity userEntity = buildGson().fromJson(resultString, UserEntity.class);
        assertThat(userEntity).isNotNull();

        UserEntity userDbEntity = repository.findById(1L).get();
        assertThat(userDbEntity).isNotNull();

        assertThat(userDbEntity).isEqualToComparingOnlyGivenFields(userEntity,
                "email", "id", "username");
    }

    @Test
    public void whenValidInput_thenCreateUser() throws Exception {
        RoleEntity role = new RoleEntity();
        role.setId(1L);

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);

        UserEntity bob = new UserEntity();
        bob.setUsername("bob1");
        bob.setPassword("123456");
        bob.setPasswordConfirm("123456");
        bob.setEmail("admin@admin.admin");
        bob.setRoles(roles);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        TokenEntity token = this.adminLogin();
        byte[] bobByte = mapper.writeValueAsBytes(bob);

        String resultString = mvcPerformAction(bobByte, token.getId(), post("/api/user"));

        UserEntity bobResult = buildGson().fromJson(resultString, UserEntity.class);
        assertThat(bobResult).isNotNull();

        UserEntity found = repository.findById(bobResult.getId()).get();
        assertThat(found).isNotNull();
        assertThat(bobResult).isEqualToComparingOnlyGivenFields(found, "id", "username");
    }

    @Test
    public void whenValidInput_thenUpdateUser() throws Exception {
        RoleEntity role = new RoleEntity();
        role.setId(1L);

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);

        UserEntity bob = new UserEntity();
        bob.setUsername("bob_to_update");
        bob.setPassword("123456");
        bob.setPasswordConfirm("123456");
        bob.setEmail("admin@admin.admin");
        bob.setRoles(roles);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        TokenEntity token = this.adminLogin();
        byte[] bobByte = mapper.writeValueAsBytes(bob);

        String resultString = mvcPerformAction(
                bobByte, token.getId(), post("/api/user"));

        UserEntity bobResult = buildGson().fromJson(resultString, UserEntity.class);
        assertThat(bobResult).isNotNull();

        bobResult.setEmail("bob_updated@bob.bob");

        byte[] bobResultByte = mapper.writeValueAsBytes(bobResult);

        String updateResultString = mvcPerformAction(
                bobResultByte, token.getId(), put("/api/user/" + bobResult.getId()));

        UserEntity bobUpdatedResult = buildGson().fromJson(updateResultString, UserEntity.class);
        assertThat(bobUpdatedResult).isNotNull();

        assertThat(bobUpdatedResult).isEqualToComparingOnlyGivenFields(bobResult, "email");
    }

    private void createTestUser(String name) {
        UserEntity user = new UserEntity();
        user.setUsername(name);
        user.setEmail("admin@admin.admin");

        repository.saveAndFlush(user);
    }
}
