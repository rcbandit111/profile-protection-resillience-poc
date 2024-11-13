package com.profile.protection.admin.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "profile-protection")
@Data
public class ProfileProtectionProperties {

    private List<DataClass> dataClasses;

    @Data
    public static class DataClass {

        private String name;
        private String activeVersionName;
        private List<Version> versions;

        @Data
        public static class Version {

            private String versionName;
            private String algorithmName;
            private String encryptAlgorithmName;
            private String encryptKeyName;
            private int encryptKeyVersion;
        }

        public Version getActualVersion() {
            return this.versions.stream()
                    .filter(version -> version.getVersionName().equals(this.activeVersionName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No active version found"));
        }
    }
}
