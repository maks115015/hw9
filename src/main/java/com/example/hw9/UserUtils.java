package com.example.hw9;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtils {

    public static UserEntity generateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(generateRandomName());
        userEntity.setBirthDate(generateRandomDateTime());
        return userEntity;
    }

    private Instant generateRandomDateTime() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2023, 10, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    private String generateRandomName() {
        return UUID.randomUUID().toString();
    }
}
