package com.profile.protection.admin.service;

import com.profile.protection.admin.dto.UserRequestDto;
import com.profile.protection.domain.Users;
import jakarta.validation.Valid;

import java.util.Optional;

public interface UserService {

    Optional<Users> create(@Valid UserRequestDto dto);

    void process();
}
