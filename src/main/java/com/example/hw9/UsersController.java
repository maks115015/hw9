package com.example.hw9;

import java.time.Instant;
import java.util.Collection;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    private final UserRepository userRepository;

    @GetMapping("/findByDate")
    public Collection<UserEntity> findByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant date) {
        return userRepository.findByBirthDate(date);
    }

    @GetMapping("/countByDate")
    public long countByBirthDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant date) {
        return userRepository.countByBirthDate(date);
    }

    @PostMapping("/save")
    public UserEntity save() {
        return userRepository.save(UserUtils.generateUser());
    }

    @GetMapping("/count")
    public long countAll() {
        return userRepository.count();
    }
}