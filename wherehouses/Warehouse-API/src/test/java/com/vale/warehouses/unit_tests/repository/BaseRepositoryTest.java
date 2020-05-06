package com.vale.warehouses.unit_tests.repository;

import com.vale.warehouses.app.model.*;
import com.vale.warehouses.app.repository.*;
import com.vale.warehouses.auth.models.RoleEntity;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.RoleRepository;
import com.vale.warehouses.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class BaseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected OwnerRepository ownerRepository;

    @Autowired
    protected SaleAgentRepository saleAgentRepository;

    @Autowired
    protected TenantRepository tenantRepository;

    @Autowired
    protected WarehouseRepository warehouseRepository;

    @Autowired
    protected LeasingContractRepository leasingContractRepository;

    @Autowired
    protected LeaseRequestRepository leaseRequestRepository;

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

        entityManager.persist(user);
    }

    protected void createOwnerUser(Map<String, RoleEntity> roleMap) {
        Owner profile = new Owner();
        profile.setAddress("some owner address");
        profile.setFirstName("owner's first name");
        profile.setLastName("owner's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());

        entityManager.persist(profile);

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleMap.get("Owner"));

        UserEntity user = new UserEntity();
        user.setUsername("owner");
        user.setPassword("$2a$10$7GUvTJcdfDFyaaTsa3GxEubmoLHUO0Z4M.b//LjDJJ6scvILT2u9G");
        user.setEmail("owner@owner.owner");
        user.setRoles(roles);
        user.setRelatedOwner(profile);

        entityManager.persist(user);
    }

    protected void createSaleAgentUser(Map<String, RoleEntity> roleMap) {
        SaleAgent profile = new SaleAgent();
        profile.setAddress("some owner address");
        profile.setFirstName("owner's first name");
        profile.setLastName("owner's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());
        profile.setFee(BigDecimal.TEN);
        profile.setRating(5);

        entityManager.persist(profile);

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleMap.get("Agent"));

        UserEntity user = new UserEntity();
        user.setUsername("saleagent");
        user.setPassword("$2a$10$dyREUv4Z46dKm4ohv18KcO.C7A1VMUOfOcAfTgxRltVEFZ2Zc.BXi");
        user.setEmail("SaleAgent@SaleAgent.SaleAgent");
        user.setRoles(roles);
        user.setRelatedSaleAgent(profile);

        entityManager.persist(user);
    }

    protected void createTenantUser(Map<String, RoleEntity> roleMap) {
        Tenant profile = new Tenant();
        profile.setAddress("some owner address");
        profile.setFirstName("owner's first name");
        profile.setLastName("owner's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());

        entityManager.persist(profile);

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleMap.get("Tenant"));

        UserEntity user = new UserEntity();
        user.setUsername("tenant");
        user.setPassword("$2a$10$bO0JiPcffNGZFl1Mf4/vzOJsi0qOXwzrwS5Af79/20TwSnRHemqbe");
        user.setEmail("Tenant@Tenant.Tenant");
        user.setRoles(roles);
        user.setRelatedTenant(profile);

        entityManager.persist(user);
    }

    protected void createInitialWarehouse() {
        Warehouse warehouse = buildWarehouse();

        warehouseRepository.save(warehouse);
    }

    protected Warehouse buildWarehouse() {
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress("some warehouse address");
        warehouse.setPricePerMonth(BigDecimal.valueOf(555.22));
        warehouse.setWidth(220.22);
        warehouse.setHeight(15.22);
        warehouse.setLength(100.00);
        warehouse.setOwner(ownerRepository.getOne(1L));
        warehouse.setCategory(Category.Fifth);
        warehouse.setType(WarehouseType.Automated);
        warehouse.setSaleAgents(new HashSet<>(saleAgentRepository.findAll()));

        return warehouse;
    }

    protected void createLeasingContract() {
        LeasingContract leasingContract = buildLeasingContract();

        leasingContractRepository.save(leasingContract);
    }

    protected LeasingContract buildLeasingContract() {
        LeasingContract leasingContract = new LeasingContract();

        leasingContract.setLeasedAt(OffsetDateTime.now());
        leasingContract.setLeasedTill(OffsetDateTime.now().plusMonths(5));
        leasingContract.setTenant(tenantRepository.findById(1L).get());
        leasingContract.setSaleAgent(saleAgentRepository.findById(1L).get());
        leasingContract.setOwner(ownerRepository.findById(1L).get());
        leasingContract.setWarehouse(warehouseRepository.findById(1L).get());

        return leasingContract;
    }

    protected void createLeaseRequest() {
        leaseRequestRepository.save(buildLeaseRequest());
    }

    protected LeaseRequest buildLeaseRequest() {
        LeaseRequest entity = new LeaseRequest();

        entity.setWarehouseType(WarehouseType.Clothing);
        entity.setTenant(tenantRepository.findById(1L).get());

        return entity;
    }

    protected Map<String, RoleEntity> createAllRoleEntities() {
        Map<String, RoleEntity> roles = new HashMap<>();
        RoleEntity admin = new RoleEntity();
        admin.setName("Admin");
        roles.put(admin.getName(), admin);

        RoleEntity saleAgent = new RoleEntity();
        saleAgent.setName("Agent");
        roles.put(saleAgent.getName(), saleAgent);

        RoleEntity owner = new RoleEntity();
        owner.setName("Owner");
        roles.put(owner.getName(), owner);

        RoleEntity tenant = new RoleEntity();
        tenant.setName("Tenant");
        roles.put(tenant.getName(), tenant);

        roles.values().forEach(r -> entityManager.persist(r));

        return roles;
    }
}
