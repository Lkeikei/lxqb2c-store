package com.lxq.collect;

import com.lxq.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  11:16
 */
@MapperScan(basePackages = "com.lxq.collect.mapper")
@SpringBootApplication
@EnableFeignClients(clients = ProductClient.class)
public class CollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class,args);
    }
}
