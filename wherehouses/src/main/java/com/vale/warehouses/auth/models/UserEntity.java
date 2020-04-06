package com.vale.warehouses.auth.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email", nullable=false, length=200)
    private String email;

    private String username;

    private String password;

    @JsonManagedReference
    @ManyToMany
    private Set<RoleEntity> roles;

//    @JsonManagedReference
//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Set<TokenEntity> tokens = new HashSet<>();

    @Transient
    private String passwordConfirm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
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

//    public Set<TokenEntity> getTokens() {
//        return tokens;
//    }
//
//    public void setTokens(Set<TokenEntity> tokens) {
//        this.tokens = tokens;
//    }
//
//    public void addToken(TokenEntity token) {
//        this.tokens.add(token);
//    }
//
//    public void removeToken(TokenEntity token) {
//        this.tokens.remove(token);
//    }
}