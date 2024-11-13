package com.profile.protection.admin.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class KeysServiceRegistry {

    private final Map<String, KeysService> keysServices = new HashMap<>();

    public void register(String forDataType, KeysService keysService) {
        if (keysServices.containsKey(forDataType)) {
            throw new IllegalArgumentException("Keys service already registered for " + forDataType);
        }

        keysServices.put(forDataType, keysService);
    }

    // later you'll use this method to get the desired service for given data type
    public KeysService provide(String forDataType) {
        return Optional.ofNullable(keysServices.get(forDataType))
                .orElseThrow(() -> new IllegalArgumentException("Keys service not found for " + forDataType));
    }
}
