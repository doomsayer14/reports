package com.internship.reports.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("1.0")
    private String projectVersion;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .directModelSubstitute(Pageable.class, String.class)
//                .directModelSubstitute(Page.class, String.class)
//                .directModelSubstitute(Sort.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.internship.reports"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(swaggerMetaData());
    }

    private ApiInfo swaggerMetaData() {
        return new ApiInfoBuilder()
                .title("EXPENSES-REPORT-API Documentation")
                .version(projectVersion)
                .build();
    }
}
