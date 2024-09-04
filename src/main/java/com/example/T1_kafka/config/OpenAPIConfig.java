package com.example.T1_kafka.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("T1 API")
                        .description("Документация для T1 API KAFKA")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("Alexandr Nazarov")
                                .email("pas58777@yandex.ru")))
                .externalDocs(new ExternalDocumentation()
                        .description("T1 Documentation")
                        .url("https://example.com/docs"));
    }
}
