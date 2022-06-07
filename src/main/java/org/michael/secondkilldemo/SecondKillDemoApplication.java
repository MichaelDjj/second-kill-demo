package org.michael.secondkilldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.michael.secondkilldemo.mapper")
public class SecondKillDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondKillDemoApplication.class, args);
    }

}
