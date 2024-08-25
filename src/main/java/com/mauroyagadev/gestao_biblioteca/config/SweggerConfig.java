package com.mauroyagadev.gestao_biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SweggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão de Biblioteca")
                        .description("API para gestão de biblioteca")
                        .contact(new Contact().name("Mauro Cesar").email("mauro.yaga.jr@gmail.com"))
                        .version("1.0.0"));
    }
}
