package com.method51.serviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class HeroApplication  {

	public static void main(String[] args) {
		SpringApplication.run(HeroApplication.class, args);
	}
}
