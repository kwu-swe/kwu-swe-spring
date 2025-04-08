package com.kwu.swe.domain.course.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponseDto {
    private Long courseId;
    private String courseName;
    private String courseNumber;
    private int score;
}
