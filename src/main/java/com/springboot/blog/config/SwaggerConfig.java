package com.springboot.blog.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    final String securitySchemeName = "bearerAuth";
    @Bean
    public OpenAPI customOpenAPI() {
        final String locUrl = "http://localhost:8182";
        final String devUrl = "https://.de";
        final String testUrl = "https://.de";
        final String preUrl = "https://.de";
        final String proUrl = "https://.grp";

        return new OpenAPI().addServersItem(new Server().url(locUrl)).addServersItem(new Server().url(
                        devUrl)).addServersItem(new Server().url(testUrl)).addServersItem(new Server().url(preUrl))
                .addServersItem(new Server().url(proUrl)).info(
                        new Info().version("v1").title("Spring Boot Blog REST APIs")
                                .description("(Spring Boot Blog REST API Documentation.)")
                                .contact(new Contact().name("Mukesh").email("mukesh@bluethink.in").url("https://www.bluethink.in/")))
                                .addSecurityItem(new SecurityRequirement()
                                .addList(securitySchemeName))
                                 .components(new Components()
                                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

    }

}
