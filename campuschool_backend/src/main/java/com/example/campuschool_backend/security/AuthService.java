package com.example.campuschool_backend.security;


import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public interface AuthService {
    Algorithm getTokenAlgorithm();
    String createAccessToken(String username);
    String verifyAccessToken(String accessToken) throws JWTVerificationException;
    String createRefreshToken(String username);
    String verifyRefreshToken(String refreshToken) throws JWTVerificationException;
    JwtTokenDto issueAccessToken(String refreshToken) throws JWTVerificationException;
}
