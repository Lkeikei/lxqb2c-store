package com.lxq.category;

import com.lxq.clients.CategoryClient;
import com.lxq.clients.ProductClient;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:26
 */
@SpringBootApplication
@MapperScan("com.lxq.category.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CategoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class, args);
    }
}
