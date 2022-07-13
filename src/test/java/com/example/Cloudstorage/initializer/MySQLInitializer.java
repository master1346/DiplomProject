package com.example.Cloudstorage.initializer;

import lombok.experimental.UtilityClass;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;

@UtilityClass
public class MySQLInitializer {
    public static MySQLContainer<?> container = new MySQLContainer<>("mysql:8.0.28");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
            "spring.datasource.url=" + container.getJdbcUrl(),
            "spring.datasource.username=" + container.getUsername(),
            "spring.datasource.password=" + container.getPassword()
            ).applyTo(applicationContext);
        }
    }
}
