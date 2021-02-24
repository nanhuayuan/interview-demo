package com.guanshi.season3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Season3Application {

    public static void main(String[] args) {
        SpringApplication.run(Season3Application.class, args);
    }

}
