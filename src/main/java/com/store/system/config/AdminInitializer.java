package com.store.system.config;

import com.store.system.entity.User;
import com.store.system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository repository, PasswordEncoder encoder) {
        return args -> {
            if(repository.findByEmail("admin@gmail.com") == null) {
                User admin = new User();
                admin.setName("admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setPhoneNo("09797078083");
                admin.setUserType("admin");
                repository.save(admin);
            }
        };
    }
}
