package aleksavukotic.primeserver.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
@EnableCaching
@EnableWebMvc
@ComponentScan(value = "aleksavukotic.primeserver")
public class WebConfiguration {
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager();
    }

}

