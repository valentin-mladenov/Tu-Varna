package com.vale.warehouses.ui.login;

import androidx.annotation.Nullable;

import com.vale.warehouses.service.model.Token;

/**
 * Authentication result : success (user details) or error message.
 */
public class LoginResult {
    @Nullable
    private Token success;
    @Nullable
    private Integer error;

    public LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    public LoginResult(@Nullable Token success) {
        this.success = success;
    }

    @Nullable
    Token getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
