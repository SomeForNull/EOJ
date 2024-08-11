package com.yunshu.eojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@MapperScan("com.yunshu.eojbackenduserservice.mapper")
@ComponentScan("com.yunshu")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class EojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EojBackendUserServiceApplication.class, args);
    }

}
