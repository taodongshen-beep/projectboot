package com.example.projectboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.projectboot.mapper"})
public class ProjectbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectbootApplication.class, args);
    }

}
