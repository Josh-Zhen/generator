package com.moonlit.generator.common.crossDomain;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域
 *
 * @author Joshua
 * @version 1.0
 * @date 16/12/2021 15:15
 * @email by.Moonlit@hotmail.com
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "*")
                .maxAge(3600 * 24)
                .allowCredentials(true);
    }
}
