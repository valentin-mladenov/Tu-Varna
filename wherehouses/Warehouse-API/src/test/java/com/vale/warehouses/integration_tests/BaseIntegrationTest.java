package com.vale.warehouses.integration_tests;

import com.google.gson.*;
import com.vale.warehouses.auth.models.RoleEntity;
import com.vale.warehouses.auth.models.TokenEntity;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.RoleRepository;
import com.vale.warehouses.auth.repository.UserRepository;
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
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class BaseIntegrationTest {
    @Autowired
    protected MockMvc mvc;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    protected TokenEntity userLogin(String username, String password) throws Exception {
        ResultActions result = mvc.perform(post("/auth/login")
                .param("username", username)
                .param("password", password)
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

    protected void baseRolesAndUsersSetup() {
        Map<String, RoleEntity> roleMap = createAllRoleEntities();

        createAdminUser(roleMap);
        createSaleAgentUser(roleMap);
        createTenantUser(roleMap);
        createOwnerUser(roleMap);
    }

    protected void createAdminUser(Map<String, RoleEntity> roleMap) {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleMap.get("Admin"));

        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword("$2a$10$1/Z488jcNgLExoA7P894n.uR7jQcFcissKqDzPEK0QK7gz30i.Cdm");
        user.setEmail("admin@admin.admin");
        user.setRoles(roles);

        userRepository.save(user);
    }

    protected void createOwnerUser(Map<String, RoleEntity> roleMap) {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleMap.get("Owner"));

        UserEntity user = new UserEntity();
        user.setUsername("owner");
        user.setPassword("$2a$10$7GUvTJcdfDFyaaTsa3GxEubmoLHUO0Z4M.b//LjDJJ6scvILT2u9G");
        user.setEmail("owner@owner.owner");
        user.setRoles(roles);

        userRepository.save(user);
    }

    protected void createSaleAgentUser(Map<String, RoleEntity> roleMap) {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleMap.get("SaleAgent"));

        UserEntity user = new UserEntity();
        user.setUsername("saleagent");
        user.setPassword("$2a$10$dyREUv4Z46dKm4ohv18KcO.C7A1VMUOfOcAfTgxRltVEFZ2Zc.BXi");
        user.setEmail("SaleAgent@SaleAgent.SaleAgent");
        user.setRoles(roles);

        userRepository.save(user);
    }

    protected void createTenantUser(Map<String, RoleEntity> roleMap) {
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleMap.get("Tenant"));

        UserEntity user = new UserEntity();
        user.setUsername("tenant");
        user.setPassword("$2a$10$bO0JiPcffNGZFl1Mf4/vzOJsi0qOXwzrwS5Af79/20TwSnRHemqbe");
        user.setEmail("Tenant@Tenant.Tenant");
        user.setRoles(roles);

        userRepository.save(user);
    }

    protected Map<String, RoleEntity> createAllRoleEntities() {
        Map<String, RoleEntity> roles = new HashMap<>();
        RoleEntity admin = new RoleEntity();
        admin.setName("Admin");
        roles.put(admin.getName(), admin);

        RoleEntity saleAgent = new RoleEntity();
        saleAgent.setName("SaleAgent");
        roles.put(saleAgent.getName(), saleAgent);

        RoleEntity owner = new RoleEntity();
        owner.setName("Owner");
        roles.put(owner.getName(), owner);

        RoleEntity tenant = new RoleEntity();
        tenant.setName("Tenant");
        roles.put(tenant.getName(), tenant);

        roleRepository.saveAll(roles.values());

        return roles;
    }
}