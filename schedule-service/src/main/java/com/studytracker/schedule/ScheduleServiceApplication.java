package com.studytracker.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableMongoAuditing
public class ScheduleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleServiceApplication.class, args);
    }
}
