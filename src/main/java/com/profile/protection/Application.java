package com.profile.protection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    /**
     * Initialize spring boot application.
     *
     * @param args main parameters
     */
    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
