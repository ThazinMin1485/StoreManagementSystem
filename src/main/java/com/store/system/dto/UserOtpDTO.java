package com.store.system.dto;

import com.store.system.entity.User;
import com.store.system.entity.UserOtp;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class UserOtpDTO {

    Long id;

    String email;

    @NotEmpty(message = "Otp must be fill")
    String otp;

    LocalDateTime expiredTime;

    User user;

    public UserOtpDTO() {
        super();
    }

    public UserOtpDTO(UserOtp otp) {
        this.id = otp.getId();
        this.email = otp.getEmail();
        this.otp = otp.getOtp();
        this.expiredTime = otp.getExpiredTime();
        this.user = otp.getUser();
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
