package acp.accs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import acp.accs.filter.AccessFilter;

@SpringBootApplication
@EnableZuulProxy
public class APIGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(APIGatewayApplication.class, args);
    }



    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}