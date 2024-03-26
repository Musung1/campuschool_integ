package com.example.campuschool_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsFilterConfiguration {
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.setAllowedOriginPatterns(Arrays.asList("*"));
//        config.addExposedHeader("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        String[] arrays = {"Authorization", "RefreshToken"};
//        config.addAllowedHeader("Authorization");
//        config.addAllowedHeader("RefreshToken");
//        source.registerCorsConfiguration("/api/**", config);
//        return new CorsFilter(source);
//    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*"); // 허용할 URL
        config.addAllowedHeader("*"); // 허용할 Header
        config.addAllowedMethod("*"); // 허용할 Http Method
        // ⭐CORS 는 해결했지만 프론트에 응답 헤더에 추가한 Authorization 이 전달되지 않는 문제 해결
        config.setExposedHeaders(Arrays.asList("Authorization","RefreshToken"));
        source.registerCorsConfiguration("/**", config); // 모든 Url에 대해 설정한 CorsConfiguration 등록
        return new CorsFilter(source);
    }
}
