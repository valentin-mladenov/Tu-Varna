package com.vale.warehouses.auth.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vale.warehouses.app.model.Owner;
import com.vale.warehouses.app.model.SaleAgent;
import com.vale.warehouses.app.model.Tenant;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email", nullable=false, length=200)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<RoleEntity> roles;

    @Transient
    private String passwordConfirm;

    @OneToOne(fetch = FetchType.LAZY)
    private Owner relatedOwner;

    @OneToOne(fetch = FetchType.LAZY)
    private Tenant relatedTenant;

    @OneToOne(fetch = FetchType.LAZY)
    private SaleAgent relatedSaleAgent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public SaleAgent getRelatedSaleAgent() {
        return relatedSaleAgent;
    }

    public void setRelatedSaleAgent(SaleAgent relatedSaleAgent) {
        this.relatedSaleAgent = relatedSaleAgent;
    }

    public Tenant getRelatedTenant() {
        return relatedTenant;
    }

    public void setRelatedTenant(Tenant relatedTenant) {
        this.relatedTenant = relatedTenant;
    }

    public Owner getRelatedOwner() {
        return relatedOwner;
    }

    public void setRelatedOwner(Owner relatedOwner) {
        this.relatedOwner = relatedOwner;
    }
}