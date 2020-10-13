package com.gql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.gql.dao")
public class SpringsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsystemApplication.class, args);
    }

}
