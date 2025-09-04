package com.store.system.repository;

import com.store.system.entity.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {

    @Query("Select u from UserOtp u where u.email = :email and u.expiredTime >= :time")
    UserOtp findByEmailAndTime(@Param("email") String email, @Param("time") LocalDateTime time);
}
