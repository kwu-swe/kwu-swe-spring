package com.kwu.swe.security.service;

import com.kwu.swe.domain.refresh.entity.RefreshToken;
import com.kwu.swe.domain.refresh.repository.RefreshTokenRepository;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.service.UserQueryService;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import com.kwu.swe.security.jwt.JwtToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService{
    private final Key key;      //security yml 파일 생성 후 app.jwt.secret에 값 넣어주기(보안을 위해 따로 연락주세요)
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserQueryService userQueryService;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenServiceImpl(@Value("${app.jwt.secret}") String key,
                            AuthenticationManagerBuilder authenticationManagerBuilder,
                            PasswordEncoder passwordEncoder,
                            UserQueryService userQueryService,
                            RefreshTokenRepository refreshTokenRepository) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.userQueryService = userQueryService;
        this.refreshTokenRepository = refreshTokenRepository;
    }
    @Override       //TODO oauth2적용시 필요 없음
    public JwtToken login(String code, String password) {
        User user = userQueryService.getUserInfo(code);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new GeneralException(ErrorStatus.AUTH_MISMATCH_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, "",
                user.getAuthorities());
        return generateToken(authentication);
    }

    @Transactional
    @Override
    public JwtToken issueTokens(String refreshToken) {
        // Refresh Token 유효성 검사
        if (!validateToken(refreshToken) || !existsRefreshToken(refreshToken)) {
            throw new GeneralException(ErrorStatus.AUTH_INVALID_REFRESH_TOKEN);
        }

        // 이전 리프레시 토큰 삭제
        refreshTokenRepository.deleteByValue(refreshToken);

        // 새로운 Authentication 객체 생성∑
        Claims claims = parseClaims(refreshToken);
        String username = claims.getSubject();
        User user = userQueryService.getUserInfo(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, "",
                user.getAuthorities());

        // 새 토큰 생성
        JwtToken newTokens = generateToken(authentication);

        return newTokens;
    }

    @Override
    public JwtToken generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + 1800000);   // 30분
        log.info("date = {}", accessTokenExpiresIn);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(now + 604800000))    // 7일
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // 새 리프레시 토큰을 Redis에 저장
        RefreshToken refreshEntity = RefreshToken.builder()
                .value(refreshToken)
                .authenticationName(authentication.getName())
                .build();
        refreshTokenRepository.save(refreshEntity);

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .code_expire(parseExpiration(accessToken))
                .refresh_expire(parseExpiration(refreshToken))
                .build();
    }

    @Override
    public Authentication getAuthentication(String accessToken) {
        // Jwt 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new GeneralException(ErrorStatus.AUTH_INVALID_TOKEN);
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication return
        // UserDetails: interface, User: UserDetails를 구현한 class
        UserDetails principal = new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            throw new GeneralException(ErrorStatus.AUTH_INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            throw new GeneralException(ErrorStatus.AUTH_TOKEN_HAS_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new GeneralException(ErrorStatus.AUTH_TOKEN_IS_UNSUPPORTED);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            throw new GeneralException(ErrorStatus.AUTH_IS_NULL);
        }
    }

    @Transactional
    @Override
    public boolean logout(String refreshToken) {
        refreshTokenRepository.deleteByValue(refreshToken);
        return true;
    }

    @Override
    public boolean existsRefreshToken(String refreshToken) {
        return refreshTokenRepository.existsByValue(refreshToken);
    }

    @Override
    public Date parseExpiration(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration();
        } catch (JwtException exception) {
            throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
        }
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}