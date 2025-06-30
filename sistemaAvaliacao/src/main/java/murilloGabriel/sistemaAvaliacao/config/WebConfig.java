package murilloGabriel.sistemaAvaliacao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 1. Aplica a regra para TODOS os endpoints do seu backend
                .allowedOrigins("http://localhost:3000") // 2. Permite requisições VINDAS DESTA origem (o seu frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 3. Libera os métodos HTTP necessários
                .allowedHeaders("*") // 4. Permite que o frontend envie qualquer cabeçalho
                .allowCredentials(true); // 5. Permite o envio de credenciais (útil para sessões/cookies)
    }
}