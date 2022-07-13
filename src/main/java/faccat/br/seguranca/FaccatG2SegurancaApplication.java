package faccat.br.seguranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FaccatG2SegurancaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(FaccatG2SegurancaApplication.class, args);
                System.out.println(new BCryptPasswordEncoder().encode("teste123"));
                
	}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
       
}
