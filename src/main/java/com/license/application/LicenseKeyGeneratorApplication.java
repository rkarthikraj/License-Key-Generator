package com.license.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.license"})
public class LicenseKeyGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(LicenseKeyGeneratorApplication.class, args);
    }
}
