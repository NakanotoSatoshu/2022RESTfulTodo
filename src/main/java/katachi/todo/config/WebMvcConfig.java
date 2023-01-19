package katachi.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // viteとの開発用に、3000からのアクセスを許可する。
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://127.0.0.1:5173/");
    }
}