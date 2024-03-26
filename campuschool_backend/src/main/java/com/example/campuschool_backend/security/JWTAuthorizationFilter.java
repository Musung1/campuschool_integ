package com.example.campuschool_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final PrincipalUserService principalUserService;
    private final JwtConst jwtConst;
    private final AuthService authService;

    public JWTAuthorizationFilter(
            AuthenticationManager authenticationManager,
            PrincipalUserService principalUserService,
            JwtConst jwtConst,
            AuthService authService
    ) {
        super(authenticationManager);
        this.principalUserService = principalUserService;
        this.jwtConst = jwtConst;
        this.authService = authService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String accessTokenHeader = request.getHeader(jwtConst.getAccessKey());
        if(accessTokenHeader == null || !accessTokenHeader.startsWith(jwtConst.getTokenPrefix())) {
            chain.doFilter(request,response);
            log.info("authorizationFilterOn");
            return;
            //TODO: 나중에 커스텀 에러로 바꿔주기
            //throw new RuntimeException();
        }
        String accessToken = accessTokenHeader.replace(jwtConst.getTokenPrefix(), "");
        String username = authService.verifyAccessToken(accessToken);
        PrincipalUser principalUser = (PrincipalUser) principalUserService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalUser,null,principalUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("end");
        chain.doFilter(request,response);

    }
}
