package com.lxq.carouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author: 娄须强
 * @CreateTime: 2023-04-30  16:02
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lxq.carouse.mapper")
@EnableCaching
public class CarouselApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }
}