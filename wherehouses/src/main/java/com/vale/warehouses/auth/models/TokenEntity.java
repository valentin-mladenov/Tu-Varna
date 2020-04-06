package com.vale.warehouses.auth.models;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name="token")
public class TokenEntity {
    @Id
    private String id;

    private OffsetDateTime expireAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private UserEntity user;

    public UUID getId() {
        return UUID.fromString(id);
    }

    public void setId(UUID id) {
        this.id = id.toString();
    }

    public OffsetDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(OffsetDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
