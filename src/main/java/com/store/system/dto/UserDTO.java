package com.store.system.dto;

import com.store.system.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

    Long id;

    @NotEmpty(message = "Name cannot be empty")
    String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Enter the valid email")
    String email;

    @NotEmpty(message = "Passowrd must be enter")
    String password;

    @NotEmpty(message = "Address cannot be empty")
    String address;

    @NotEmpty(message = "Phone no must be enter")
    String phoneNo;

    String userType;

    Boolean enabled;

    public UserDTO() {
        super();
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.phoneNo = user.getPhoneNo();
        this.userType = user.getUserType();
        this.enabled = user.getEnabled();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name cannot be empty") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name cannot be empty") String name) {
        this.name = name;
    }

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

    public @NotEmpty(message = "Address cannot be empty") String getAddress() {
        return address;
    }

    public void setAddress(@NotEmpty(message = "Address cannot be empty") String address) {
        this.address = address;
    }

    public @NotEmpty(message = "Phone no must be enter") String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(@NotEmpty(message = "Phone no must be enter") String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
