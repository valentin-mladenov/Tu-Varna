package com.vale.warehouses.service.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class User extends JSONObject implements Serializable {
    @SerializedName("id")
    private long id;

    @SerializedName("username")
    private String userName;

    @SerializedName("email")
    private String email;

    @SerializedName("roles")
    private Set<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

    public void addRole(Role role) {
        if (this.hasRole(role)) {
            return;
        }

        this.roles.add(role);
    }

    public void removeRole(Role role) {
        if (!this.hasRole(role)) {
            return;
        }

        this.roles.remove(role);
    }
}
