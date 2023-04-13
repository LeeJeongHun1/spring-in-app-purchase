
package com.springinapppurchase.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .info(new Info().version("v1").title("spring API").description("spring API 입니다."))
                .addServersItem(new Server().url("/"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

//    @Bean
//    public GroupedOpenApi groupOpenApiV10() {
//        String paths[] = {"/v2/**"};
//        //String packagesToscan[] = {"test.org.springdoc.api.app68.api.user", "test.org.springdoc.api.app68.api.store"};
//        String packagesToscan[] = {};
//        return GroupedOpenApi.builder().group("Ohmoss-api v2.0").pathsToMatch(paths).packagesToScan(packagesToscan)
//                .build();
//    }
//    @Bean
//    public GroupedOpenApi groupOpenApiV11() {
//        String paths[] = {"/v1.1/**"};
//        //String packagesToscan[] = {"test.org.springdoc.api.app68.api.user", "test.org.springdoc.api.app68.api.store"};
//        String packagesToscan[] = {};
//        return GroupedOpenApi.builder().group("nbhd-api v1.1").pathsToMatch(paths).packagesToScan(packagesToscan)
//                .build();
//    }

}
