package com.method51.servicea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import com.method51.swagger2.SpringfoxConfiguration;
import com.method51.swagger2.Swagger2Application;

@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class CalcApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Object[] { Swagger2Application.class, SpringfoxConfiguration.class,
                CalcApplication.class }, args);
    }

}
