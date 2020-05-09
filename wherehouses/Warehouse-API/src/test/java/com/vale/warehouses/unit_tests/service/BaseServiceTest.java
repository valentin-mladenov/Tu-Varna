package com.vale.warehouses.unit_tests.service;

import com.vale.warehouses.app.model.*;
import com.vale.warehouses.app.repository.*;
import com.vale.warehouses.auth.models.RoleEntity;
import com.vale.warehouses.auth.models.UserEntity;
import com.vale.warehouses.auth.repository.UserRepository;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

public class BaseServiceTest {
    protected OffsetDateTime nowDateTime = OffsetDateTime.now();

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected OwnerRepository ownerRepository;

    @MockBean
    protected SaleAgentRepository saleAgentRepository;

    @MockBean
    protected TenantRepository tenantRepository;

    @MockBean
    protected WarehouseRepository warehouseRepository;

    @MockBean
    protected LeaseRequestRepository leaseRequestRepository;

    @MockBean
    protected LeasingContractRepository leasingContractRepository;

    @Before
    public void setUp() {
        setupUserRepositoryMock();

        setupTenantRepositoryMock();

        setupSaleAgentRepositoryMock();

        setupOwnerRepositoryMock();

        setupWarehouseRepositoryMock();

        setupLeaseRequestRepositoryMock();

        setupLeasingContractRepositoryMock();
    }

    protected void setupUserRepositoryMock() {
        UserEntity user = buildUser();

        Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(user);

        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(java.util.Optional.of(user));

        Mockito.when(userRepository.findAll())
                .thenReturn(Collections.singletonList(user));

        Mockito.when(userRepository.save(Mockito.any(UserEntity.class)))
                .thenReturn(user);
    }

    protected UserEntity buildUser() {
        UserEntity user = new UserEntity();
        user.setUsername("admin");
        user.setPassword("$2a$10$1/Z488jcNgLExoA7P894n.uR7jQcFcissKqDzPEK0QK7gz30i.Cdm");
        user.setEmail("admin@admin.admin");
        user.setId(1L);

        RoleEntity adminRole = new RoleEntity();
        adminRole.setName("Admin");
        adminRole.setId(1L);

        user.setRoles(new HashSet<>(Collections.singletonList(adminRole)));

        return user;
    }

    protected void setupTenantRepositoryMock() {
        Tenant entity = buildTenant();

        Mockito.when(tenantRepository.findById(entity.getId()))
                .thenReturn(java.util.Optional.of(entity));

        Mockito.when(tenantRepository.findAll())
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(tenantRepository.save(Mockito.any(Tenant.class)))
                .thenReturn(entity);
    }

    protected Tenant buildTenant() {
        Tenant profile = new Tenant();
        profile.setAddress("some tenant address");
        profile.setFirstName("tenant's first name");
        profile.setLastName("tenant's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());
        profile.setId(1L);

        return profile;
    }

    protected void setupOwnerRepositoryMock() {
        Owner entity = buildOwner();

        Mockito.when(ownerRepository.findById(entity.getId()))
                .thenReturn(java.util.Optional.of(entity));

        Mockito.when(ownerRepository.findAll())
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(ownerRepository.save(Mockito.any(Owner.class)))
                .thenReturn(entity);
    }

    protected Owner buildOwner() {
        Owner profile = new Owner();
        profile.setAddress("some owner address");
        profile.setFirstName("owner's first name");
        profile.setLastName("owner's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());
        profile.setId(1L);

        return profile;
    }

    protected void setupSaleAgentRepositoryMock() {
        SaleAgent entity = buildSaleAgent();

        Mockito.when(saleAgentRepository.findById(entity.getId()))
                .thenReturn(java.util.Optional.of(entity));

        Mockito.when(saleAgentRepository.findAll())
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(saleAgentRepository.save(Mockito.any(SaleAgent.class)))
                .thenReturn(entity);
    }

    protected SaleAgent buildSaleAgent() {
        SaleAgent profile = new SaleAgent();
        profile.setAddress("some tenant address");
        profile.setFirstName("tenant's first name");
        profile.setLastName("tenant's last name");
        profile.setPhoneNumber("1963574585");
        profile.setUniqueCode(UUID.randomUUID().toString());
        profile.setId(1L);

        return profile;
    }

    protected void setupWarehouseRepositoryMock() {
        Warehouse entity = buildWarehouse();

        Long saleAgentId =  new ArrayList<>(entity.getSaleAgents()).get(0).getId();
        Mockito.when(warehouseRepository.findBySaleAgentsId(saleAgentId))
                .thenReturn(Collections.singletonList(entity));

        Long ownerId = entity.getOwner().getId();
        Mockito.when(warehouseRepository.findByOwnerId(ownerId))
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(warehouseRepository.findById(entity.getId()))
                .thenReturn(java.util.Optional.of(entity));

        Mockito.when(warehouseRepository.findAll())
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(warehouseRepository.save(Mockito.any(Warehouse.class)))
                .thenReturn(entity);
    }

    protected Warehouse buildWarehouse() {
        Warehouse entity = new Warehouse();
        entity.setAddress("some warehouse address");
        entity.setPricePerMonth(BigDecimal.valueOf(555.22));
        entity.setWidth(220.22);
        entity.setHeight(15.22);
        entity.setLength(100.00);
        entity.setSaleAgents(new HashSet<>(saleAgentRepository.findAll()));
        entity.setOwner(ownerRepository.findAll().get(0));
        entity.setCategory(Category.Fifth);
        entity.setType(WarehouseType.Automated);
        entity.setId(1L);

        return entity;
    }

    protected void setupLeaseRequestRepositoryMock() {
        LeaseRequest entity = buildLeaseRequest();

        Long tenantId = entity.getTenant().getId();
        Mockito.when(leaseRequestRepository.findByTenantIdAndLeasingContractIsNull(tenantId))
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leaseRequestRepository.findByLeasingContractIsNull())
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leaseRequestRepository.findById(entity.getId()))
                .thenReturn(java.util.Optional.of(entity));

        Mockito.when(leaseRequestRepository.findAll())
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leaseRequestRepository.save(Mockito.any(LeaseRequest.class)))
                .thenReturn(entity);
    }

    protected LeaseRequest buildLeaseRequest() {
        LeaseRequest entity = new LeaseRequest();
        entity.setWarehouseType(WarehouseType.Clothing);
        entity.setTenant(tenantRepository.findAll().get(0));
        entity.setId(1L);

        return entity;
    }

    protected void setupLeasingContractRepositoryMock() {
        LeasingContract entity = buildLeasingContract();

        // OWNER Access
        Long ownerId = entity.getOwner().getId();
        Mockito.when(leasingContractRepository.findByOwnerId(ownerId))
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leasingContractRepository.findByOwnerId(ownerId))
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leasingContractRepository.findByOwnerIdAndLeasedTillAfter
                (ownerId, nowDateTime.minusDays(1)))
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leasingContractRepository.findByOwnerIdAndLeasedTillBetweenOrderByLeasedTillDesc(
                ownerId, nowDateTime.minusDays(1), nowDateTime.plusMonths(1)))
                .thenReturn(Collections.singletonList(entity));

        // SALE AGENT Access
        Long saleAgentId = entity.getSaleAgent().getId();
        Mockito.when(leasingContractRepository.findBySaleAgentId(saleAgentId))
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leasingContractRepository.findBySaleAgentIdAndLeasedAtBetween(
                saleAgentId, nowDateTime.minusDays(1), nowDateTime.plusMonths(1)))
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leasingContractRepository.findBySaleAgentIdAndLeasedTillAfter(
                saleAgentId, nowDateTime.minusDays(1)))
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leasingContractRepository.findBySaleAgentIdAndLeasedTillBetweenOrderByLeasedTillDesc(
                saleAgentId, nowDateTime.minusDays(1), nowDateTime.plusMonths(1)))
                .thenReturn(Collections.singletonList(entity));

        // REGULAR access
        Mockito.when(leasingContractRepository.findById(entity.getId()))
                .thenReturn(java.util.Optional.of(entity));

        Mockito.when(leasingContractRepository.findAll())
                .thenReturn(Collections.singletonList(entity));

        Mockito.when(leasingContractRepository.save(Mockito.any(LeasingContract.class)))
                .thenReturn(entity);
    }

    protected LeasingContract buildLeasingContract() {
        LeasingContract entity = new LeasingContract();
        Warehouse warehouse = warehouseRepository.findAll().get(0);

        entity.setLeasedAt(nowDateTime);
        entity.setLeasedTill(nowDateTime.plusMonths(5));
        entity.setTenant(tenantRepository.findAll().get(0));
        entity.setSaleAgent(saleAgentRepository.findAll().get(0));
        entity.setOwner(warehouse.getOwner());
        entity.setWarehouse(warehouse);
        entity.setId(1L);

        return entity;
    }
}
