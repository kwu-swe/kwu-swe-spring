package com.kwu.swe.domain.course.service;

import com.kwu.swe.domain.course.dto.RegisterCourseRequestDto;
import com.kwu.swe.domain.course.entity.Course;
import com.kwu.swe.domain.course.entity.CourseType;
import com.kwu.swe.domain.course.repository.CourseRepository;
import com.kwu.swe.global.util.EnumConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseCommandServiceImpl implements CourseCommandService{
    private final CourseRepository courseRepository;
    @Override
    public Long registerCourse(RegisterCourseRequestDto dto) {
        Course newCourse = Course.builder()
                .courseName(dto.getCourseName())
                .courseNumber(dto.getCourseNumber())
                .score(dto.getScore())
                .courseType(EnumConvertUtil.convert(CourseType.class, dto.getCourseType()))
                .build();
        return courseRepository.save(newCourse).getId();
    }
}
