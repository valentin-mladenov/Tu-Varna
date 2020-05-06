package com.vale.warehouses.integration_tests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.reflect.TypeToken;
import com.vale.warehouses.Start;
import com.vale.warehouses.app.model.LeasingContract;
import com.vale.warehouses.auth.models.TokenEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, classes= Start.class)

public class LeasingContractControllerIntegrationTest extends BaseIntegrationTest {
    @Test
    public void givenOneLeasingContract_whenGetOwnerLeasingContracts_thenStatus200() throws Exception {
        createInitialWarehouse();
        createLeasingContract();

        TokenEntity token = this.userLogin("owner", "123456");

        ResultActions result = mvc.perform(
                get("/api/leasingContract/forOwner/" + token.getUser().getRelatedOwner().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId())
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<LeasingContract>>(){}.getType();
        List<LeasingContract> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<LeasingContract> dbEntities = leasingContractRepository.findAll();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void givenLeasingContracts_whenGetOwnerLeasingContracts_thenStatus200() throws Exception {
        createInitialWarehouse();
        createLeasingContract();
        createLeasingContract();

        TokenEntity token = this.userLogin("owner", "123456");

        Long ownerId = token.getUser().getRelatedOwner().getId();

        ResultActions result = mvc.perform(
                get("/api/leasingContract/forOwner/" + ownerId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<LeasingContract>>(){}.getType();
        List<LeasingContract> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<LeasingContract> dbEntities = leasingContractRepository.findByOwnerId(ownerId);
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void givenAdminUser_whenGetLeasingContract_thenStatus200() throws Exception {
        createInitialWarehouse();
        createLeasingContract();

        TokenEntity token = this.userLogin("admin", "123456");

        ResultActions result = mvc.perform(get("/api/leasingContract")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<LeasingContract>>(){}.getType();
        List<LeasingContract> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<LeasingContract> dbEntities = leasingContractRepository.findAll();
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void givenSaleAgentUser_whenGetLeasingContract_thenStatus200() throws Exception {
        createInitialWarehouse();
        createLeasingContract();

        TokenEntity token = this.userLogin("saleagent", "123456");

        ResultActions result = mvc.perform(get("/api/leasingContract/1")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        LeasingContract resultEntity = buildGson().fromJson(resultString, LeasingContract.class);
        assertThat(resultEntity).isNotNull();

        LeasingContract dbEntity = leasingContractRepository.findById(resultEntity.getId()).get();
        assertThat(dbEntity).isNotNull();

        assertThat(resultEntity).isEqualToComparingOnlyGivenFields(dbEntity,
                "id");

        assertThat(resultEntity.getLeasedTill() ).isEqualTo(dbEntity.getLeasedTill());
        assertThat(resultEntity.getLeasedAt()).isEqualTo(dbEntity.getLeasedAt());
    }

    @Test
    public void whenValidInput_thenCreateLeasingContract() throws Exception {
        createInitialWarehouse();
        LeasingContract inputEntity = buildLeasingContract();
        inputEntity.getSaleAgent().setWarehouses(new HashSet<>());
        inputEntity.getWarehouse().setSaleAgents(new HashSet<>());

        TokenEntity token = this.userLogin("saleagent", "123456");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(OffsetDateTime.class, new JsonSerializer<OffsetDateTime>() {
            @Override
            public void serialize(
                    OffsetDateTime offsetDateTime,
                    JsonGenerator jsonGenerator,
                    SerializerProvider serializerProvider
            ) throws IOException {
                jsonGenerator.writeString(offsetDateTime.format(DateTimeFormatter.ISO_INSTANT));
            }
        });
        mapper.registerModule(simpleModule);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        byte[] byteData = mapper.writeValueAsBytes(inputEntity);

        String resultString = mvcPerformAction(byteData, token.getId(), post("/api/leasingContract"));

        LeasingContract outputResult = buildGson().fromJson(resultString, LeasingContract.class);
        assertThat(outputResult).isNotNull();

        LeasingContract found = leasingContractRepository.findById(outputResult.getId()).get();

        found.getSaleAgent().setWarehouses(new HashSet<>());
        assertThat(found).isNotNull();

        assertThat(outputResult).isEqualToComparingOnlyGivenFields(found,
                "id");

        assertThat(outputResult.getLeasedAt().toInstant()).isEqualTo(found.getLeasedAt().toInstant());
        assertThat(outputResult.getLeasedTill().toInstant()).isEqualTo(found.getLeasedTill().toInstant());
    }

    @Test
    public void givenLeasingContracts_whenGetSaleAgentLeasingContracts_thenStatus200() throws Exception {
        createInitialWarehouse();
        createInitialWarehouse();
        createLeasingContract();
        createLeasingContract();

        TokenEntity token = this.userLogin("saleagent", "123456");

        Long saleAgentId = token.getUser().getRelatedSaleAgent().getId();

        ResultActions result = mvc.perform(
                get("/api/leasingContract/forSaleAgent/" + saleAgentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        Type listType = new TypeToken<List<LeasingContract>>(){}.getType();
        List<LeasingContract> resultEntities = buildGson().fromJson(resultString, listType);
        assertThat(resultEntities).isNotNull();

        List<LeasingContract> dbEntities = leasingContractRepository.findBySaleAgentId(saleAgentId);
        assertThat(dbEntities).isNotNull();

        assertThat(dbEntities).hasSize(resultEntities.size());
    }

    @Test
    public void whenValidInput_thenUpdateLeasingContract() throws Exception {
        createInitialWarehouse();
        LeasingContract leasingContract = buildLeasingContract();
        leasingContract.getSaleAgent().setWarehouses(new HashSet<>());
        leasingContract.getWarehouse().setSaleAgents(new HashSet<>());

        TokenEntity token = this.userLogin("saleagent", "123456");

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(OffsetDateTime.class, new JsonSerializer<OffsetDateTime>() {
            @Override
            public void serialize(
                OffsetDateTime offsetDateTime,
                JsonGenerator jsonGenerator,
                SerializerProvider serializerProvider
            ) throws IOException {
                jsonGenerator.writeString(offsetDateTime.format(DateTimeFormatter.ISO_INSTANT));
            }
        });
        mapper.registerModule(simpleModule);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        byte[] byteData = mapper.writeValueAsBytes(leasingContract);

        String postString = mvcPerformAction(byteData, token.getId(), post("/api/leasingContract"));

        LeasingContract postResult = buildGson().fromJson(postString, LeasingContract.class);
        assertThat(postResult).isNotNull();
        leasingContract.setId(postResult.getId());

        LeasingContract found = leasingContractRepository.findById(postResult.getId()).get();
        found.getSaleAgent().setWarehouses(new HashSet<>());

        assertThat(found).isNotNull();
        assertThat(leasingContract).isEqualToComparingOnlyGivenFields(found,
                "id", "leasedAt", "leasedTill");

        OffsetDateTime leasedTill = OffsetDateTime.now().plusMonths(12);
        postResult.setLeasedTill(leasedTill);
        leasingContract.setLeasedTill(leasedTill);

        byte[] bytePutData = mapper.writeValueAsBytes(postResult);

        String resultPutString = mvcPerformAction(bytePutData, token.getId(),
                put("/api/leasingContract/" + postResult.getId()));
        LeasingContract putResult = buildGson().fromJson(resultPutString, LeasingContract.class);
        assertThat(putResult).isNotNull();

        LeasingContract foundPut = leasingContractRepository.findById(putResult.getId()).get();
        found.getSaleAgent().setWarehouses(new HashSet<>());
        assertThat(foundPut).isNotNull();

        assertThat(leasingContract).isEqualToComparingOnlyGivenFields(foundPut,
                "id", "leasedAt", "leasedTill");

        assertThat(foundPut.getLeasedTill()).isEqualTo(leasedTill);
    }
}
