package com.sxq.mymail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MymailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymailApplication.class, args);
    }

}
