package com.store.system.entity;

import com.store.system.dto.UserOtpDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_otp")
public class UserOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String otp;

    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

    @Column(nullable = false)
    LocalDateTime expiredTime;

    public UserOtp() {
        super();
    }

    public UserOtp(UserOtpDTO dto) {
        this.id = dto.getId();
        this.email =dto.getEmail();
        this.otp = dto.getOtp();
        this.expiredTime = dto.getExpiredTime();
        this.user = dto.getUser();
    }

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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
