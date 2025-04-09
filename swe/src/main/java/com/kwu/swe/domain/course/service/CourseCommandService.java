package com.kwu.swe.domain.course.service;

import com.kwu.swe.domain.course.dto.request.RegisterCourseRequestDto;

public interface CourseCommandService {

    Long registerCourse(RegisterCourseRequestDto dto);
}
