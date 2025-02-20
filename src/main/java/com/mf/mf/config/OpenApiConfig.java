package com.mf.mf.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.version}")
    private String appVersion;

    @Bean
    public OpenAPI applicationOpenApi() throws IOException {
        return new OpenAPI()
                .info(new Info()
                        .title("API " + appName)
                        .version("V" + appVersion)
                        .description("Open API definitions and documentations for API " + appName));

    }

}
