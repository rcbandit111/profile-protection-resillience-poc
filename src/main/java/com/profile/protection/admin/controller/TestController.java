package com.profile.protection.admin.controller;

import com.profile.protection.admin.service.KeysServiceRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    private final KeysServiceRegistry keysServiceRegistry;

    public TestController(KeysServiceRegistry keysServiceRegistry) {
        this.keysServiceRegistry = keysServiceRegistry;
    }

    @GetMapping("/test")
    public ResponseEntity<String> encrypt() {
        String response = keysServiceRegistry.process(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
