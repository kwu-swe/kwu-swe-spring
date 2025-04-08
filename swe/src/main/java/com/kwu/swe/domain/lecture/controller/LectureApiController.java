package com.kwu.swe.domain.lecture.controller;

import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;
import com.kwu.swe.domain.lecture.service.LectureCommandService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureApiController {

    private final LectureCommandService lectureCommandService;

    @PostMapping
    public ApiResponseDto<Long> registerLecture(@RequestParam String professorNumber,
                                                @RequestBody RegisterLectureRequestDto dto) {
        return ApiResponseDto.onSuccess(lectureCommandService.registerLecture(professorNumber, dto));
    }

//    @GetMapping
//    public ApiResponseDto<List<LectureResponseDto>> getAllLecture()
}
