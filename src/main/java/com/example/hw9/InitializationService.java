package com.example.hw9;

import java.util.Collection;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class InitializationService {

    private final UserRepository userRepository;
    private final long expectedCountInDb;
    private final long batchSize;

    @EventListener(ApplicationStartedEvent.class)
    void initDataBase() {
        long currentCount = userRepository.count();
        System.out.println("Start initialize DB, existed users count: " + currentCount);

        while (currentCount < expectedCountInDb) {
            Collection<UserEntity> users = createUsersBatch();

            long timeBefore = System.nanoTime();
            userRepository.saveAllAndFlush(users);
            long timeAfter = System.nanoTime();

            System.out.println("execution time: " + (timeAfter - timeBefore) / 1_000_000 + " ms");

            currentCount += users.size();
            System.out.println("Already initialized count: " + currentCount);
            System.out.println("Progress: " + (currentCount * 100 / expectedCountInDb) + "%");
        }

        System.out.println("Finish initialize DB, existed users count: " + currentCount);
        System.out.println("Ready to work!");

    }

    private Collection<UserEntity> createUsersBatch() {
        return Stream.iterate(0, i -> i + 1)
                .limit(batchSize)
                .map(i -> UserUtils.generateUser())
                .toList();
    }
}
