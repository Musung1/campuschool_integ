package com.example.campuschool_backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
    private final JwtConst jwtConst;

    @Override
    public Algorithm getTokenAlgorithm() {
        return Algorithm.HMAC256(jwtConst.getTokenSecretKey());
    }

    @Override
    public String createAccessToken(String username) {
        return JWT.create()
                .withSubject("accessKey")
                .withClaim("username",username)
                .withExpiresAt(new Date(System.currentTimeMillis()+jwtConst.getAccessTokenExpirationTime()))
                .sign(getTokenAlgorithm());
    }

    @Override
    public String verifyAccessToken(String accessToken) throws JWTVerificationException {
        return JWT.require(getTokenAlgorithm())
                .build()
                .verify(accessToken)
                .getClaim("username").asString();
    }

    @Override
    public String createRefreshToken(String username) {
        return JWT.create()
                .withSubject("refreshKey")
                .withClaim("username",username)
                .withExpiresAt(new Date(System.currentTimeMillis()+jwtConst.getRefreshTokenExpirationTime()))
                .sign(getTokenAlgorithm());
    }

    @Override
    public String verifyRefreshToken(String refreshToken) throws JWTVerificationException {
        return JWT.require(getTokenAlgorithm())
                .build()
                .verify(refreshToken)
                .getClaim("username").asString();
    }
    // refresh Token 이 유효하면 accessToken 발급
    @Override
    public JwtTokenDto issueAccessToken(String refreshToken) throws JWTVerificationException {
        String username = verifyRefreshToken(refreshToken);
        String accessToken = createAccessToken(username);
        return JwtTokenDto.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .build();
    }
}
