package acp.accs.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CalcApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalcApplication.class, args);
    }
}