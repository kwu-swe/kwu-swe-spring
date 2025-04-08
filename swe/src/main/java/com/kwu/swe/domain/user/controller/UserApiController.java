package com.kwu.swe.domain.user.controller;

import com.kwu.swe.domain.user.dto.EditUserInfoRequestDto;
import com.kwu.swe.domain.user.dto.RegisterUserRequestDto;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.domain.user.service.UserCommandService;
import com.kwu.swe.global.util.EnumConvertUtil;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserCommandService userCommandService;

    @PostMapping
    public ApiResponseDto<Long> registerUser(@RequestParam String role,
                                             @RequestBody RegisterUserRequestDto dto) {
        return ApiResponseDto.onSuccess(
                userCommandService.registerUser(
                        dto,
                        EnumConvertUtil.convert(Role.class, role)));
    }

    @PatchMapping
    public ApiResponseDto<Long> updateUserInfo(@RequestParam String studentNumber,
                                               @RequestBody EditUserInfoRequestDto dto) {
        return ApiResponseDto.onSuccess(
                userCommandService.updateUserInfo(
                        studentNumber,
                        dto));
    }

}
