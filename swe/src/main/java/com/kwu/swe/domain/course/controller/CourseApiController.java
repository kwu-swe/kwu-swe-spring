package com.kwu.swe.domain.course.controller;

import com.kwu.swe.domain.course.dto.RegisterCourseRequestDto;
import com.kwu.swe.domain.course.service.CourseCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseApiController {

    private final CourseCommandService courseCommandService;

    @PostMapping
    public Long registerCourse(@RequestBody RegisterCourseRequestDto dto) {
        return courseCommandService.registerCourse(dto);
    }
}
