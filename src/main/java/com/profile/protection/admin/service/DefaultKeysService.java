package com.profile.protection.admin.service;

public class DefaultKeysService implements KeysService {

    private final ProfileProtectionProperties.DataClass             dataClass;
    private final ProfileProtectionProperties.DataClass.Version     actualVersion;

    public DefaultKeysService(ProfileProtectionProperties.DataClass dataClass) {
        this.dataClass = dataClass;
        this.actualVersion = dataClass.getActualVersion();
    }

    public String forDataType() {
        return this.dataClass.getName();
    }

    @Override
    public String encrypt(String plaintext) {
        return "";
    }

    @Override
    public String decrypt(String ciphertext) {
        return "";
    }
}
