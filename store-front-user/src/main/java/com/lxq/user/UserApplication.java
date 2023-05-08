package com.lxq.user;

import com.lxq.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  10:05
 */
@MapperScan("com.lxq.user.mapper")
@SpringBootApplication
@EnableCaching
@EnableFeignClients(clients = {UserClient.class})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
