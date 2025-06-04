package com.kwu.swe.global.controller;

import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @GetMapping("/npe")
    ApiResponseDto<String> testDiscord() {
        String str = null;
        return ApiResponseDto.onSuccess(str.toString());
    }
}
