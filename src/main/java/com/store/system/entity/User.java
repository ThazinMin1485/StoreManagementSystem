package com.store.system.entity;

import com.store.system.dto.UserDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    String address;

    @Column(name = "phone_no", nullable = false)
    String phoneNo;

    @Column(name = "user_type", nullable = false)
    String userType;

    public User() {
        super();
    }

    public User(UserDTO dto) {
        this.id =dto.getId();
        this.address =dto.getAddress();
        this.email =dto.getEmail();
        this.userType = dto.getUserType();
        this.phoneNo = dto.getPhoneNo();
        this.name = dto.getName();
        this.password = dto.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
