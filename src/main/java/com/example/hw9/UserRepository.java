package com.example.hw9;

import java.time.Instant;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Collection<UserEntity> findByBirthDate(Instant date);

    long countByBirthDate(Instant date);
}
