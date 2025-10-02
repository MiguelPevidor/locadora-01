package locadora.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://192.168.1.210:3005",
                                "http://186.218.63.106:3005", "http://162.240.169.65:3005",
                                "https://www.siadfou.com.br",
                                "http://172.18.0.3:3000", "https://siadfou.com.br", "https://www.siadfou.com.br",
                                "https://api.siadfou.com.br", "https://seduma.siadfou.com.br/", "https://mapa.siadfou.com.br/")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
