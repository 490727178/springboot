package com.example.springbootmybtisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.example.springbootmybtisplus.mapper")
@EnableAspectJAutoProxy
public class SpringBootMybtisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybtisPlusApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
