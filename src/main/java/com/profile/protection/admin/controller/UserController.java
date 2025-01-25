package com.profile.protection.admin.controller;

import com.profile.protection.admin.dto.UserRequestDto;
import com.profile.protection.admin.service.UserService;
import com.profile.protection.domain.Users;
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

    /**
     * Create user request
     * <p>
     * {
     *     "loginName": "logv3ei64yynName",
     *     "fullName": "fu3lflb6Nyame",
     *     "email": "eym3jfv5abil",
     *     "password": "pvassword",
     *     "pmleId": 1202534,
     *     "status": "ENABLED",
     *     "roles": [
     *         {
     *           "roleCode": "VIEW",
     *           "projectType": "CRM"
     *         },
     *         {
     *           "roleCode": "VIEW",
     *           "projectType": "CRM"
     *         },
     *         {
     *           "roleCode": "VIEW",
     *           "projectType": "CRM"
     *         },
     *         {
     *           "roleCode": "BRANDOPTIMAL",
     *           "projectType": "FCN"
     *         }
     *       ]
     * }
     */
    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody UserRequestDto dto) {
        userService.create(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Get user request
     * <p>
     * GET http://localhost:8080/user/74d6c47c-7cb3-43d1-9308-c326cac8ccf6
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<Object> get(@PathVariable("uuid") UUID uuid) {
        Optional<Users> users = userService.get(uuid);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Update user
     * <p>
     * {
     *     "loginName": "logv3ei64yynName",
     *     "fullName": "fu3lflb6Nyame",
     *     "email": "eym3jfv5abil",
     *     "password": "pvassword",
     *     "pmleId": 1202534,
     *     "status": "ENABLED",
     *     "roles": [
     *         {
     *           "roleCode": "VIEW",
     *           "projectType": "CRM"
     *         },
     *         {
     *           "roleCode": "VIEW",
     *           "projectType": "CRM"
     *         },
     *         {
     *           "roleCode": "VIEW",
     *           "projectType": "CRM"
     *         },
     *         {
     *           "roleCode": "BRANDOPTIMAL",
     *           "projectType": "FCN"
     *         }
     *       ]
     * }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody UserRequestDto userRequestDto) {
        Optional<Users> users = userService.update(id, userRequestDto);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
