package com.example.jqssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching //开起缓存
public class JqSsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JqSsmApplication.class, args);
    }

}
