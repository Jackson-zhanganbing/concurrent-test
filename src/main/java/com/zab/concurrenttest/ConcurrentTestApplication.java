package com.zab.concurrenttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ConcurrentTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrentTestApplication.class, args);
    }

}
