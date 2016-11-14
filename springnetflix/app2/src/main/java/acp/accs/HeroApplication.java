package acp.accs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import acp.accs.swagger2.SpringfoxConfiguration;
import acp.accs.swagger2.Swagger2Application;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class HeroApplication {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }



    public static void main(String[] args) {
        // SpringApplication.run(HeroApplication.class, args);
        SpringApplication.run(new Object[] {  Swagger2Application.class,
                SpringfoxConfiguration.class,HeroApplication.class }, args);
    }
}