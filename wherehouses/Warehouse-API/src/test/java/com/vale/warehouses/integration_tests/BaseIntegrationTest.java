package com.vale.warehouses.integration_tests;

import com.google.gson.*;
import com.vale.warehouses.auth.models.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class BaseIntegrationTest {
    @Autowired
    protected MockMvc mvc;

    protected TokenEntity adminLogin() throws Exception {

        ResultActions result = mvc.perform(post("/auth/login")
                .param("username", "admin")
                .param("password", "123456")
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        TokenEntity token = buildGson().fromJson(resultString, TokenEntity.class);

        return token;
    }

    protected String mvcPerformAction(
            byte[] objectByte, String tokenId, MockHttpServletRequestBuilder post
    ) throws Exception {
        return mvc.perform(post
                .header("Authorization", "Bearer " + tokenId)
                .accept("application/json;charset=UTF-8")
                .contentType(MediaType.APPLICATION_JSON).content(objectByte))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
    }

    protected Gson buildGson() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .registerTypeAdapter(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
                    @Override
                    public OffsetDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                            throws JsonParseException {
                        return OffsetDateTime.parse(json.getAsString());
                    }
                }).create();

        return gson;
    }
}
