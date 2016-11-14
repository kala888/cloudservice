package acp.accs.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import acp.accs.swagger2.SpringfoxConfiguration;
import acp.accs.swagger2.Swagger2Application;

@EnableDiscoveryClient
@SpringBootApplication
public class CalcApplication {
    public static void main(String[] args) {
        // SpringApplication.run(CalcApplication.class, args);
        SpringApplication.run(new Object[] {  Swagger2Application.class,
                SpringfoxConfiguration.class,CalcApplication.class }, args);
    }
}