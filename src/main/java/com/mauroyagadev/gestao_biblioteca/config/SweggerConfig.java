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
                        .description("Esta é uma API de gestão de biblioteca. Ela permite a manipulação de livros, usuários e empréstimos em um banco de dados relacional." +
                                " Aqui você pode realizar operações de CRUD para cada uma das entidades." +
                                " A API foi desenvolvida para atender os requisitos de um desafio de desenvolvimento back-end." +
                                " Para mais informações, acesse o repositório do projeto no GitHub: " +
                                " [github.com/mauroyaga/sring-boot-api-rest-gestao-biblioteca](https://github.com/mauroyaga/sring-boot-api-rest-gestao-biblioteca) ")
                        .contact(new Contact()
                                .name("Mauro Cesar Yaga Junior")
                                .email("mauro.yaga.jr@gmail.com"))
                        .version("1.0.0"));
    }
}
