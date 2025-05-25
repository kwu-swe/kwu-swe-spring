package com.kwu.swe.security.controller;

import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import com.kwu.swe.security.jwt.JwtToken;
import com.kwu.swe.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/login")
    public ApiResponseDto<JwtToken> login(@RequestParam String code) {
        return ApiResponseDto.onSuccess(tokenService.login(code));
    }

    @DeleteMapping("/logout")
    public ApiResponseDto<String> logout(@RequestParam String refreshToken) {
        tokenService.logout(refreshToken);
        return ApiResponseDto.onSuccess("logout");
    }

    @PutMapping("/re-issue")
    public ApiResponseDto<JwtToken> refresh(@RequestParam String refreshToken) {
        return ApiResponseDto.onSuccess(tokenService.issueTokens(refreshToken));
    }
}
