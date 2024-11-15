package com.playdata.orderservice.common.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS(Cross-Origin Resource Sharing) => 교차 출처 자원 공유
// CORS: 웹 어플리케이션이 다른 도메인에서 리소스를 요청할 때 발생하는 보안 문제를 해결하기 위해 사용

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://orderservice-front92.s3-website.ap-northeast-2.amazonaws.com")
                .allowedMethods("*") // get, post 요청 허용 여부
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
