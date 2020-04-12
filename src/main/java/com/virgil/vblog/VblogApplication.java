package com.virgil.vblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.virgil.vblog.dao.mapper")
public class VblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VblogApplication.class, args);
    }

}
