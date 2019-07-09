package com.adb.adblab.generalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.adb.adblab.generalservice.mapper")
@ComponentScan(basePackages = {"com.adb.adblab.generalservice"})
public class GeneralserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralserviceApplication.class, args);
    }

}
