package com.profile.protection.admin.controller;

import com.profile.protection.admin.dto.UserRequestDto;
import com.profile.protection.admin.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<Object> encrypt() {
        userService.process();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
