package com.profile.protection.admin.service;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KeysServiceRegistrar {

    private final KeysServiceRegistry           keysServiceRegistry;
    private final ProfileProtectionProperties   protectionProperties;

    // read all the properties before the application starts, create a service for each data class, register all of them by data type
    @EventListener(ContextRefreshedEvent.class)
    public void register() {
        protectionProperties.getDataClasses()
                .stream()
                .map(dataClass -> {
                    // create a service for each data class
                    DefaultKeysService defaultKeysService = new DefaultKeysService(dataClass);
                    return defaultKeysService;
                })
                .forEach(defaultKeysService ->
                        keysServiceRegistry.register(defaultKeysService.forDataType(), defaultKeysService));

    }
}

