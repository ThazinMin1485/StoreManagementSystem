package com.store.system.service;

import com.store.system.entity.UserOtp;
import com.store.system.repository.UserOtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserOtpService {

    @Autowired
    private UserOtpRepository repository;

    public boolean verifyCode(String email, String code, LocalDateTime time) {
        UserOtp otp = repository.findByEmailAndTime(email, time);
        if (otp != null && otp.getOtp().equals(code)) {
            return true;
        } else {
            return false;
        }
    }

    public void saveOtp(UserOtp otp) {
        repository.save(otp);
    }
}
