package com.allo.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author FuYiCheng
 * @date 2023-02-05 18:08
 * @description:启动类
 * @version:
 */
@SpringBootApplication
@MapperScan("com.allo.system.mapper")
public class ServiceAuthApplication {

    public static void main(String[]args){
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
