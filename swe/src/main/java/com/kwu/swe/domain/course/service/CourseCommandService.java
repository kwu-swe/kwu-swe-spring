package com.kwu.swe.domain.course.service;

import com.kwu.swe.domain.course.dto.RegisterCourseRequest;

public interface CourseCommandService {

    Long registerCourse(RegisterCourseRequest dto);
}
