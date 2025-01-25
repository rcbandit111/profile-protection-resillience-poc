package com.profile.protection.admin.service;

import com.profile.protection.admin.dto.UserRequestDto;
import com.profile.protection.admin.mapper.UserAssembler;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RolesRepository rolesRepository;

    private UserAssembler userAssembler;

    public UserServiceImpl(UserRepository userRepository, RolesRepository rolesRepository, UserAssembler userAssembler) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.userAssembler = userAssembler;
    }

    @Override
    public Optional<Users> create(UserRequestDto dto) {

        Users users = Users.builder()
                .email(dto.getEmail())
                .loginName(dto.getLoginName())
                .password(dto.getPassword())
                .createdAt(OffsetDateTime.now())
                .build();

        Users insertedUser = userRepository.saveAndFlush(users);

        List<Roles> rolesList = dto.getRoles().stream()
                .map(roleDto -> Roles.builder()
                        .userId(insertedUser.getId())
                        .roleCode(roleDto.getRoleCode())
                        .projectType(roleDto.getProjectType())
                        .createdAt(OffsetDateTime.now())
                        .build())
                .collect(Collectors.toList());

        rolesRepository.saveAllAndFlush(rolesList);


        return Optional.of(insertedUser);
    }

    @Override
    public Optional<Users> get(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public Optional<Users> update(UUID id, UserRequestDto userRequestDto) {
        return Optional.ofNullable(userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setLoginName(userRequestDto.getLoginName());
                    existingUser.setEmail(userRequestDto.getEmail());
                    existingUser.setPassword(userRequestDto.getPassword());
                    existingUser.setModifiedAt(OffsetDateTime.now());

                    // Update roles logic
                    List<Roles> updatedRoles = userRequestDto.getRoles().stream()
                            .map(roleDto -> Roles.builder()
                                    .userId(existingUser.getId())
                                    .roleCode(roleDto.getRoleCode())
                                    .projectType(roleDto.getProjectType())
                                    .createdAt(existingUser.getCreatedAt())
                                    .modifiedAt(OffsetDateTime.now())
                                    .build())
                            .collect(Collectors.toList());

                    // Remove existing roles and replace with new ones
                    existingUser.getRoles().clear();
                    existingUser.getRoles().addAll(updatedRoles);

                    return userRepository.saveAndFlush(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id)));
    }

    private Users mapToEntity(UserRequestDto dto) {
        return Users.builder()
                .loginName(dto.getLoginName())
                .password(dto.getPassword())
                .email(dto.getEmail())
//                .status(dto.getStatus())
                .roles(dto.getRoles().stream()
                        .map(roleDto -> Roles.builder()
                                .roleCode(roleDto.getRoleCode())
                                .projectType(roleDto.getProjectType())
                                .createdAt(OffsetDateTime.now())
                                .modifiedAt(OffsetDateTime.now())
                                .build())
                        .collect(Collectors.toList()))
                .createdAt(OffsetDateTime.now())
                .modifiedAt(OffsetDateTime.now())
                .build();
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
