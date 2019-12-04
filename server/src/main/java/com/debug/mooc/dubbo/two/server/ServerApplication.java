package com.debug.mooc.dubbo.two.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:spring/spring-jdbc.xml", "classpath:spring/spring-dubbo.xml"})
@MapperScan(basePackages = "com.debug.mooc.dubbo.two.model.mapper")
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
