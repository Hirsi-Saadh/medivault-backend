package com.codewithsaadh.medivaultbackend.configurations;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

@Configuration
@ConfigurationProperties(prefix = "myapp.medi-vault")
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // Set the location to store temporary files (optional)
        factory.setLocation("/tmp");
        return factory.createMultipartConfig();
    }

}

