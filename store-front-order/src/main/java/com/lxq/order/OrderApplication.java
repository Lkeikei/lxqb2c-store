package com.lxq.order;

import com.lxq.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-01  17:29
 */
@MapperScan(basePackages = "com.lxq.order.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
