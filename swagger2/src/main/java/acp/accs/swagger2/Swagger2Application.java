package acp.accs.swagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.web.ErrorPageFilter;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

import acp.accs.simple.CalcApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class Swagger2Application extends SpringBootServletInitializer {

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        application.getSources().remove(ErrorPageFilter.class);
        return super.run(application);
    }



    public static void main(String[] args) {
        SpringApplication.run(new Object[] { CalcApplication.class,Swagger2Application.class, SpringfoxConfiguration.class }, args);
    }

}
