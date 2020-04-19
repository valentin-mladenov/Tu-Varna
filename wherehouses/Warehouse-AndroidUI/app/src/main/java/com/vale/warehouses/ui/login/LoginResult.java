package com.vale.warehouses.ui.login;

import androidx.annotation.Nullable;

import com.vale.warehouses.data.model.Token;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private Token success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable Token success) {
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
