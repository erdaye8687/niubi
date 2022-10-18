package com.ljy.logindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.ljy.logindemo.dao"})
public class LoginDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginDemoApplication.class, args);
    }

}
