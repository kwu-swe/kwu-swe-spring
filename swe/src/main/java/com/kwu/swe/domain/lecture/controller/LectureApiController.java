package com.kwu.swe.domain.lecture.controller;

import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;
import com.kwu.swe.domain.lecture.service.LectureCommandService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureApiController {

    private final LectureCommandService lectureCommandService;

    @PostMapping
    public ApiResponseDto<Long> registerLecture(@RequestBody RegisterLectureRequestDto dto) {
        return ApiResponseDto.onSuccess(lectureCommandService.registerLecture(dto));
    }
}
