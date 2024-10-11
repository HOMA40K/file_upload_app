package com.example.file_upload_app.config;

import com.example.file_upload_app.entity.User;
import com.example.file_upload_app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        try {
            userRepository.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return args -> {

            userRepository.save(new User("user1", passwordEncoder.encode("password1"), "USER"));
            userRepository.save(new User("admin", passwordEncoder.encode("password2"), "ADMIN"));
        };
    }
}
