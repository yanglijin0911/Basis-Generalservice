package com.adb.adblab.generalservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.adb.adblab.generalservice.mapper.*")
@ComponentScan(basePackages = {"com.adb.adblab.generalservice"})
public class GeneralserviceApplicationTests {

    @Test
    public void contextLoads() {
    }

}
