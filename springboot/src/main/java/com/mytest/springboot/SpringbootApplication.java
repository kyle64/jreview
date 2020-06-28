package com.mytest.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@ServletComponentScan
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);

        String version = SpringVersion.getVersion();
        String version1 = SpringBootVersion.getVersion();
    }

}
