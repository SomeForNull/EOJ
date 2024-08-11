package com.yunshu.eojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@MapperScan("com.yunshu.eojbackendquestionservice.mapper")
@ComponentScan("com.yunshu")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class EojBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EojBackendQuestionServiceApplication.class, args);
    }

}
