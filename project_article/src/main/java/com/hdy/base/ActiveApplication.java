package com.hdy.base;

import com.hdy.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableCaching
@EnableEurekaClient
@SpringBootApplication
public class ActiveApplication {
    public static void main(String[] args){
        SpringApplication.run(ActiveApplication.class);
    }
    public IdWorker idWorker(){return new IdWorker(1,1);}
}
