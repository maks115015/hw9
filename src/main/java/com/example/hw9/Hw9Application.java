package com.example.hw9;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@SpringBootApplication
public class Hw9Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw9Application.class, args);
    }

    @Profile("init")
    @Bean
    InitializationService initializationService(UserRepository userRepository,
                                                @Value("${db.init.size:41000000}") long expectedUsersCountInDb,
                                                @Value("${db.init.batch-size:20000}") long batchSize) {
        return new InitializationService(userRepository, expectedUsersCountInDb, batchSize);
    }
}