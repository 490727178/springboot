package com.example.springbootseata;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.example.springbootseata.mapper")
public class SpringBootSeataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSeataApplication.class, args);
    }

}
