package com.vale.warehouses.integration_tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;
import com.vale.warehouses.Start;
import com.vale.warehouses.app.model.LeaseRequest;
import com.vale.warehouses.auth.models.TokenEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes= Start.class)

public class LeaseRequestControllerIntegrationTest extends BaseIntegrationTest {
    @Test
    public void givenOneNotCompletedLeaseRequest_whenGetLeaseRequest_thenStatus200() throws Exception {
        createLeaseRequest();

        TokenEntity token = this.userLogin("saleagent", "123456");

        ResultActions result = mvc.perform(
                get("/api/leaseRequest/notCompeted")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<LeaseRequest>>(){}.getType();
        List<LeaseRequest> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<LeaseRequest> dbEntities = leaseRequestRepository.findByLeasingContractIsNull();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void givenLeaseRequests_whenGetLeaseRequests_thenStatus200() throws Exception {
        createLeaseRequest();
        createLeaseRequest();

        TokenEntity token = this.userLogin("saleagent", "123456");

        ResultActions result = mvc.perform(
                get("/api/leaseRequest/notCompeted" )
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<LeaseRequest>>(){}.getType();
        List<LeaseRequest> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<LeaseRequest> dbEntities = leaseRequestRepository.findByLeasingContractIsNull();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void givenAdminUser_whenGetLeaseRequest_thenStatus200() throws Exception {
        createLeaseRequest();

        TokenEntity token = this.userLogin("admin", "123456");

        ResultActions result = mvc.perform(get("/api/leaseRequest")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<LeaseRequest>>(){}.getType();
        List<LeaseRequest> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<LeaseRequest> dbEntities = leaseRequestRepository.findAll();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void givenSaleAgentUser_whenGetLeaseRequest_thenStatus200() throws Exception {
        createLeaseRequest();

        TokenEntity token = this.userLogin("saleagent", "123456");

        ResultActions result = mvc.perform(get("/api/leaseRequest/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        LeaseRequest resultEntity = buildGson().fromJson(resultString, LeaseRequest.class);
        assertThat(resultEntity).isNotNull();

        LeaseRequest dbEntity = leaseRequestRepository.findById(resultEntity.getId()).get();
        assertThat(dbEntity).isNotNull();

        assertThat(resultEntity).isEqualToComparingOnlyGivenFields(dbEntity,
                "id", "warehouseType");
    }

    @Test
    public void whenValidInput_thenCreateLeaseRequest() throws Exception {
        LeaseRequest inputEntity = buildLeaseRequest();
        inputEntity.getTenant().setId(null);
        inputEntity.getTenant().setUniqueCode(UUID.randomUUID().toString());

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        byte[] byteData = mapper.writeValueAsBytes(inputEntity);

        String resultString = mvcPerformAction(byteData, null, post("/auth/createLeaseRequest"));

        LeaseRequest outputResult = buildGson().fromJson(resultString, LeaseRequest .class);
        assertThat(outputResult).isNotNull();

        LeaseRequest found = leaseRequestRepository.findById(outputResult.getId()).get();

        assertThat(found).isNotNull();

        assertThat(outputResult).isEqualToComparingOnlyGivenFields(found,
                "id", "warehouseType");
    }

    @Test
    public void givenLeaseRequests_whenGetSaleAgentNotCompletedLeaseRequests_thenStatus200() throws Exception {
        createInitialWarehouse();
        createLeasingContract();
        createLeaseRequest();

        TokenEntity token = this.userLogin("saleagent", "123456");

        ResultActions result = mvc.perform(
                get("/api/leaseRequest/notCompeted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<LeaseRequest>>(){}.getType();
        List<LeaseRequest> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<LeaseRequest> dbEntities = leaseRequestRepository.findByLeasingContractIsNull();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }
}
