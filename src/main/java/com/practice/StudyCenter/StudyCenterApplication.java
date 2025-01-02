package com.practice.StudyCenter;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class StudyCenterApplication{
    public static void main(String[] args) {
        SpringApplication.run(StudyCenterApplication.class, args);
    }

    @PostConstruct
    public void initSuperAmdim(){

    }
}
