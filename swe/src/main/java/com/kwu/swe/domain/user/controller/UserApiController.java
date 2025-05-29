package com.kwu.swe.domain.user.controller;

import com.kwu.swe.domain.user.dto.EditUserInfoRequestDto;
import com.kwu.swe.domain.user.dto.RegisterUserRequestDto;
import com.kwu.swe.domain.user.dto.UserResponseDto;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.service.UserCommandService;
import com.kwu.swe.domain.user.service.UserQueryService;
import com.kwu.swe.global.util.EnumConvertUtil;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping
    public ApiResponseDto<Long> registerUser(@RequestParam String role,
                                             @RequestBody RegisterUserRequestDto dto) {
        return ApiResponseDto.onSuccess(
                userCommandService.registerUser(
                        dto,
                        EnumConvertUtil.convert(Role.class, role)));
    }

    @PatchMapping
    public ApiResponseDto<Long> updateUserInfo(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
                                               @RequestBody EditUserInfoRequestDto dto) {
        log.info("userDetails.username : {}", userDetails.getUsername());
        return ApiResponseDto.onSuccess(
                userCommandService.updateUserInfo(
                        userDetails.getUsername(),
                        dto));
    }

    @GetMapping
    public ApiResponseDto<UserResponseDto> getUserInfo(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        User user = userQueryService.getUserInfo(userDetails.getUsername());
        UserResponseDto result = UserResponseDto.builder()
                .phoneNumber(user.getPhoneNumber())
                .code(user.getCode())
                .name(user.getName())
                .role(user.getRole())
                .build();
        return ApiResponseDto.onSuccess(result);
    }

    @PatchMapping("/password")
    public ApiResponseDto<Long> editOnlyPassword(@RequestParam String code,
                                                 @RequestParam String password) {
        return ApiResponseDto.onSuccess(userCommandService.updatePassword(code, password));
    }

}
