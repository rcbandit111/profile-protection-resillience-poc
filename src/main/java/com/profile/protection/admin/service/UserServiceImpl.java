package com.profile.protection.admin.service;

import com.profile.protection.admin.dto.UserRequestDto;
import com.profile.protection.domain.Roles;
import com.profile.protection.repository.RolesRepository;
import com.profile.protection.repository.UserRepository;
import com.profile.protection.domain.Users;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RolesRepository rolesRepository;

    public UserServiceImpl(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Optional<Users> create(UserRequestDto dto) {

        Users users = Users.builder()
                .email(dto.getEmail())
                .loginName(dto.getLoginName())
                .fullName(dto.getFullName())
                .password(dto.getPassword())
                .createdAt(OffsetDateTime.now())
                .build();

        Users insertedUser = userRepository.saveAndFlush(users);

        List<Roles> rolesList = dto.getRoles().stream()
                .map(roleDto -> Roles.builder()
                        .user(insertedUser)
                        .roleCode(roleDto.getRoleCode())
                        .projectType(roleDto.getProjectType())
                        .createdAt(OffsetDateTime.now())
                        .build())
                .collect(Collectors.toList());

        rolesRepository.saveAllAndFlush(rolesList);


        return Optional.of(insertedUser);
    }

    // Test for circuitBreaker

    @TimeLimiter(name = "controller")
    @CircuitBreaker(name = "myService", fallbackMethod = "circuitBreakerFallback")
    @Retry(name = "myService", fallbackMethod = "retryFallback")
    public void process() {
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // Fallback method for CircuitBreaker
    public String circuitBreakerFallback(Throwable t) {
        return "Circuit breaker fallback: " + t.getMessage();
    }

    // Fallback method for Retry
    public String retryFallback(Throwable t) {
        return "Retry fallback: " + t.getMessage();
    }
}
