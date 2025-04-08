package com.kwu.swe.domain.lecture.dto.response;

import com.kwu.swe.domain.course.dto.response.CourseResponseDto;
import com.kwu.swe.domain.lecture.entity.LectureStatus;
import com.kwu.swe.domain.lecture.entity.Semester;
import com.kwu.swe.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Data;

import java.time.Year;

@Data
@Builder
public class LectureResponseDto {
    private int sizeLimit;
    private Year year;
    private LectureStatus lectureStatus;
    private Semester semester;
    private UserResponseDto professor;
    private CourseResponseDto courseResponseDto;
}
