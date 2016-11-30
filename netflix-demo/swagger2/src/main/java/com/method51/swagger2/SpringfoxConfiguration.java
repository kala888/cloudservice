package com.method51.swagger2;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@Configuration
@EnableSwagger2
@ComponentScan("cn.com.bbt.openapi")
public class SpringfoxConfiguration {

    @Autowired
    private TypeResolver typeResolver;

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      .apis(RequestHandlerSelectors.any())
                                                      .paths(PathSelectors.any())
                                                      .build()
                                                      .pathMapping("/")
                                                      .directModelSubstitute(LocalDate.class, String.class)
                                                      .genericModelSubstitutes(ResponseEntity.class)
                                                      .alternateTypeRules(AlternateTypeRules.newRule(
                                                              typeResolver.resolve(DeferredResult.class,
                                                                                   typeResolver.resolve(
                                                                                           ResponseEntity.class,
                                                                                           WildcardType.class)),
                                                              typeResolver.resolve(WildcardType.class)))
                                                      .useDefaultResponseMessages(false);
    }
}
