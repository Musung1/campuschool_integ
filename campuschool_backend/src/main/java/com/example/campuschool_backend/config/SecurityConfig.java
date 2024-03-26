package com.example.campuschool_backend.config;
import com.example.campuschool_backend.security.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthService authService;
    private final CorsFilterConfiguration corsFilterConfiguration;
    private final ObjectMapper objectMapper;
    private final PrincipalUserService principalUserService;
    private final JwtConst jwtConst;
    private final AuthenticationConfiguration authenticationConfiguration;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/class/**").authenticated()
                                .anyRequest().permitAll()
                )
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .apply(new CustomDsl());
        return http.build();
    }
    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager, objectMapper, authService,jwtConst);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");

            http
                    .addFilter(corsFilterConfiguration.corsFilter())
                    .addFilter(jwtAuthenticationFilter)
                    .addFilter(new JWTAuthorizationFilter(authenticationManager,principalUserService, jwtConst, authService))
                    .addFilterBefore(new FilterExceptionHandlerFilter(), BasicAuthenticationFilter.class);
        }

    }
}
