package com.profile.protection.admin.controller;

import com.profile.protection.admin.service.KeysServiceRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class EncryptionController {

    private final KeysServiceRegistry keysServiceRegistry;

    public EncryptionController(KeysServiceRegistry keysServiceRegistry) {
        this.keysServiceRegistry = keysServiceRegistry;
    }

    @GetMapping("/test")
    public ResponseEntity<Object> encrypt() {
        keysServiceRegistry.process(null);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
