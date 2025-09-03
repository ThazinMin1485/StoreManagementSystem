package com.store.system.service;

import com.store.system.entity.User;
import com.store.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

}
