package com.example.campuschool_backend.security;
import com.example.campuschool_backend.dto.auth.LoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
@Slf4j
@Transactional
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final AuthService authService;
    private final JwtConst jwtConst;
    // usernamePasswordAuthenticationFilter 새로 구현
    // 1. request -> loginForm 객체(username, password) 생성
    // 1. (id,password)로 usernamePasswordToken(authentication 객체) 만듬 principal : id, credential : password
    // 3. authenticationManager에 인증 위임
    // 4. authenticationManager은 내부의 authenticatinProvier 호출
    // 5. authenticationProvier은 미리 구현한 userDetailService의 loadUserByUsername 호출 , userDetail 가져옴
    // 6. 유저 있으면 authentication 객체 반환 (principal : userDetail) + Authorities
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = null;
        LoginForm loginForm = null;
        log.info("authentication");
        try {
            loginForm = objectMapper.readValue(request.getInputStream(),LoginForm.class);
            request.setAttribute("username",loginForm.getUsername());
            request.setAttribute("password",loginForm.getPassword());
        } catch (IOException e) {
            log.info(e.toString());
            //throw new RuntimeException(e);
        }
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(),loginForm.getPassword());
            authentication = authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            log.info(e.getMessage());
            //throw new RuntimeException(e);
        }
        return authentication;
    }
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException, ServletException {
        PrincipalUser principalUser = (PrincipalUser) authResult.getPrincipal();
        String username = principalUser.getUsername();
        String refreshToken = authService.createRefreshToken(username);
        JwtTokenDto jwtTokenDto = authService.issueAccessToken(refreshToken);

        response.addHeader(jwtConst.getAccessKey(), jwtConst.getTokenPrefix() + jwtTokenDto.getAccessToken());
        response.addHeader(jwtConst.getRefreshKey(), jwtConst.getTokenPrefix() + jwtTokenDto.getRefreshToken());
        chain.doFilter(request,response);
    }
}
