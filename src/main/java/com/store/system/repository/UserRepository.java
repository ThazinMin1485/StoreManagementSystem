package com.store.system.repository;

import com.store.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("Select u from User u where u.email=:email")
    public User findByEmail(String email);

}
