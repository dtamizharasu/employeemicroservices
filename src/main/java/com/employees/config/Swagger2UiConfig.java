package com.employees.config;

import com.employees.model.Employee;
import com.employees.model.Project;
import com.employees.vo.ResponseVo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger2UiConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .ignoredParameterTypes(Employee.class, Project.class, ResponseVo.class, Iterable.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.employees"))
                .paths(regex("/api/employee.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Swagger 2 API For Employee Microservices",
                null,
                "1.0",
                "Terms of Service",
                "Tamizharasu",
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/"
        );

        return apiInfo;
    }
}
