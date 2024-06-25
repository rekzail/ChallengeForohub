package zaildev.forohub.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Challenge Foro Hub Alura")
                        .description("Api que contiene las operaciones CRUD para Tópicos")
                        .contact(new Contact()
                                .name("Moises Zail Vegas")
                                .email("rekcahet@gmail.com"))
                )
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                .components(
                        new Components()
                                .addSecuritySchemes("bearer-key",
                                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }
}
