package com.profile.protection.admin.controller;

import com.profile.protection.admin.service.KeysService;
import com.profile.protection.admin.service.KeysServiceRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/profile-protection", produces = MediaType.APPLICATION_JSON_VALUE)
public class EncryptionController {

    private final KeysServiceRegistry keysServiceRegistry;

    @PostMapping("/{dataClassName}/encrypt")
    public String encrypt(@PathVariable String dataClassName, @RequestBody String plaintext) {
        KeysService encryptionService = keysServiceRegistry.provide(dataClassName);
        return encryptionService.encrypt(plaintext);
    }
}
