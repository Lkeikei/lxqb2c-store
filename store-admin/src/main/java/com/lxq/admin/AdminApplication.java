package com.lxq.admin;

import com.lxq.clients.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-05-05  16:59
 */
@SpringBootApplication
@MapperScan("com.lxq.admin.mapper")
@EnableCaching
@EnableFeignClients(clients = {UserClient.class, CategoryClient.class,
        SearchClient.class, ProductClient.class, OrderClient.class})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
