package locadora.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Locadora - DevWeb II")
                        .version("v1")
                        .description("API para gerenciamento de uma locadora de Livros.")
                        .contact(new Contact().name("Miguel").email("mpevidorcruz@gmail.com"))
                );
    }
}
