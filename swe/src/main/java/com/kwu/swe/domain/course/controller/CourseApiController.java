package com.kwu.swe.domain.course.controller;

import com.kwu.swe.domain.course.dto.CourseResponseDto;
import com.kwu.swe.domain.course.dto.RegisterCourseRequestDto;
import com.kwu.swe.domain.course.entity.Course;
import com.kwu.swe.domain.course.service.CourseCommandService;
import com.kwu.swe.domain.course.service.CourseQueryService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseApiController {

    private final CourseCommandService courseCommandService;
    private final CourseQueryService courseQueryService;

    @PostMapping
    public ApiResponseDto<Long> registerCourse(@RequestBody RegisterCourseRequestDto dto) {
        return ApiResponseDto.onSuccess(courseCommandService.registerCourse(dto));
    }

    @GetMapping
    public ApiResponseDto<List<CourseResponseDto>> getAllCourse() {
        List<Course> allCourse = courseQueryService.getAllCourse();
        List<CourseResponseDto> result = allCourse.stream()
                .map(course -> CourseResponseDto.builder()
                        .courseName(course.getCourseName())
                        .courseNumber(course.getCourseNumber())
                        .score(course.getScore())
                        .courseId(course.getId())
                        .build())
                .toList();
        return ApiResponseDto.onSuccess(result);
    }
}
