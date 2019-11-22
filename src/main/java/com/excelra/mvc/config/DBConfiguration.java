package com.excelra.mvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBConfiguration.class);

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Profile("dev")
    @Bean
    public void devDatabaseConnection() {
        LOGGER.info("DB connection for DEV - DriverClassName {}, url {} ", driverClassName, url);
    }

    @Profile("qa")
    @Bean
    public void qaDatabaseConnection() {
        LOGGER.info("DB connection for QA - DriverClassName {}, url {} ", driverClassName, url);
    }

    @Profile("uat")
    @Bean
    public void uatDatabaseConnection() {
        LOGGER.info("DB connection for UAT - DriverClassName {}, url {} ", driverClassName, url);
    }

    @Profile("prod")
    @Bean
    public void prodDatabaseConnection() {
        LOGGER.info("DB connection for PRODUCTION - DriverClassName {}, url {} ", driverClassName, url);
    }

}
