package com.hdy.base;

import com.hdy.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient//开启“在注册中心里 发现其他微服务”的功能 Qa->Base
@EnableFeignClients
public class FriendApplication {
    public static void main(String[] args)  {
         SpringApplication.run( FriendApplication.class);
    }

    @Bean //IdWorker放入ioc容器 （1. @Bean +返回值  2.三层注解 ）
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
