package com.example.mysqlr2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableTransactionManagement
public class MysqlR2Application {

    public static void main(String[] args) {
        SpringApplication.run(MysqlR2Application.class, args);
    }

}


