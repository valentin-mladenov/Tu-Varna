package com.vale.warehouses.integration_tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.vale.warehouses.Start;
import com.vale.warehouses.app.model.Category;
import com.vale.warehouses.app.model.Warehouse;
import com.vale.warehouses.auth.models.RoleEntity;
import com.vale.warehouses.auth.models.TokenEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes= Start.class)

public class WarehouseControllerIntegrationTest extends BaseIntegrationTest {
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
        List<RoleEntity> roleEntities = roleRepository.findAll();

        if(roleEntities.size() > 0) {
            return;
        }

        baseRolesAndUsersSetup();
    }

    @Test
    public void givenOneWarehouse_whenGetOwnerWarehouses_thenStatus200() throws Exception {
        createInitialWarehouse();

        TokenEntity token = this.userLogin("owner", "123456");

        ResultActions result = mvc.perform(
                get("/api/warehouse/forOwner/" + token.getUser().getRelatedOwner().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<Warehouse>>(){}.getType();
        List<Warehouse> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<Warehouse> dbEntities = warehouseRepository.findAll();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void givenWarehouses_whenGetOwnerWarehouses_thenStatus200() throws Exception {
        createInitialWarehouse();
        createInitialWarehouse();

        TokenEntity token = this.userLogin("owner", "123456");

        ResultActions result = mvc.perform(
                get("/api/warehouse/forOwner/" + token.getUser().getRelatedOwner().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<Warehouse>>(){}.getType();
        List<Warehouse> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<Warehouse> dbEntities = warehouseRepository.findAll();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void givenAdminUser_whenGetWarehouse_thenStatus200() throws Exception {
        createInitialWarehouse();
        TokenEntity token = this.userLogin("admin", "123456");

        ResultActions result = mvc.perform(get("/api/warehouse/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Warehouse resultEntity = buildGson().fromJson(resultString, Warehouse.class);
        assertThat(resultEntity).isNotNull();

        Warehouse dbEntity = warehouseRepository.findById(1L).get();
        assertThat(dbEntity).isNotNull();

        assertThat(dbEntity).isEqualToComparingOnlyGivenFields(resultEntity,
                "address", "id", "width");
    }

    @Test
    public void whenValidInput_thenCreateWareHouse() throws Exception {
        Warehouse warehouse = buildWarehouse();
        warehouse.getSaleAgents().forEach(sa -> sa.setWarehouses(new HashSet<>()));

        TokenEntity token = this.userLogin("owner", "123456");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        byte[] byteData = mapper.writeValueAsBytes(warehouse);

        String resultString = mvcPerformAction(byteData, token.getId(), post("/api/warehouse"));

        Warehouse outputResult = buildGson().fromJson(resultString, Warehouse.class);
        assertThat(outputResult).isNotNull();

        Warehouse found = warehouseRepository.findById(outputResult.getId()).get();
        found.setSaleAgents(new HashSet<>());

        assertThat(found).isNotNull();
        assertThat(outputResult).isEqualToComparingOnlyGivenFields(found,
                "id", "address", "width");
    }

    @Test
    public void givenWarehouses_whenGetSaleAgentWarehouses_thenStatus200() throws Exception {
        createInitialWarehouse();
        createInitialWarehouse();

        TokenEntity token = this.userLogin("saleagent", "123456");

        ResultActions result = mvc.perform(
                get("/api/warehouse/forSaleAgent/" + token.getUser().getRelatedSaleAgent().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<Warehouse>>(){}.getType();
        List<Warehouse> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<Warehouse> dbEntities = warehouseRepository.findAll();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void whenValidInput_thenUpdateWareHouse() throws Exception {
        Warehouse warehouse = buildWarehouse();
        warehouse.getSaleAgents().forEach(sa -> sa.setWarehouses(new HashSet<>()));

        TokenEntity token = this.userLogin("owner", "123456");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        byte[] byteData = mapper.writeValueAsBytes(warehouse);

        String postString = mvcPerformAction(byteData, token.getId(), post("/api/warehouse"));

        Warehouse postResult = buildGson().fromJson(postString, Warehouse.class);
        assertThat(postResult).isNotNull();

        Warehouse found = warehouseRepository.findById(postResult.getId()).get();
        found.setSaleAgents(new HashSet<>());

        assertThat(found).isNotNull();
        assertThat(postResult).isEqualToComparingOnlyGivenFields(found,
                "id", "address", "width");


        postResult.setCategory(Category.First);
        byte[] bytePutData = mapper.writeValueAsBytes(postResult);

        String resultPutString = mvcPerformAction(bytePutData, token.getId(),
                put("/api/warehouse/" + postResult.getId()));
        Warehouse putResult = buildGson().fromJson(resultPutString, Warehouse.class);
        assertThat(putResult).isNotNull();

        Warehouse foundPut = warehouseRepository.findById(putResult.getId()).get();
        found.setSaleAgents(new HashSet<>());

        assertThat(found).isNotNull();
        assertThat(putResult).isEqualToComparingOnlyGivenFields(foundPut,
                "id", "address", "width");

        assertThat(foundPut.getCategory()).isEqualTo(Category.First);
    }
}
