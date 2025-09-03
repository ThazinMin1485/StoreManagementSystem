package com.store.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Enter the valid email")
    String email;

    @NotEmpty(message = "Passowrd must be enter")
    String password;

    public @NotEmpty(message = "Email cannot be empty") @Email(message = "Enter the valid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email cannot be empty") @Email(message = "Enter the valid email") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Passowrd must be enter") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Passowrd must be enter") String password) {
        this.password = password;
    }
}
