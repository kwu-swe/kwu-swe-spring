package com.kwu.swe.security.service;

import com.kwu.swe.security.jwt.JwtToken;
import org.springframework.security.core.Authentication;

import java.util.Date;

public interface TokenService {

    JwtToken login(String code);
    JwtToken issueTokens(String refreshToken);

    JwtToken generateToken(Authentication authentication);

    Authentication getAuthentication(String accessToken);

    boolean validateToken(String token);

    boolean logout(String refreshToken);

    boolean existsRefreshToken(String refreshToken);

    Date parseExpiration(String token);
}
