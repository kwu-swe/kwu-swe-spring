package com.kwu.swe.domain.lecture.dto.response;

import com.kwu.swe.domain.course.dto.response.CourseResponseDto;
import com.kwu.swe.domain.lecture.entity.LectureStatus;
import com.kwu.swe.domain.lecture.entity.Semester;
import com.kwu.swe.domain.user.dto.UserResponseDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class LectureResponseDto {
    private Long lectureId;
    private int sizeLimit;
    private int year;
    private LectureStatus lectureStatus;
    private Semester semester;
    private LocalDateTime createdAt;
    private UserResponseDto professor;
    private CourseResponseDto courseResponseDto;
    @Builder.Default
    private Map<String, Long> lectureTimeAndLocation = new HashMap<>();
}
