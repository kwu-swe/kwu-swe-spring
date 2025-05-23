package com.kwu.swe.domain.course.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class CourseResponseDto {
    private Long courseId;
    private String courseName;
    private String courseNumber;
    private int score;
    private LocalDateTime createdAt;
}
