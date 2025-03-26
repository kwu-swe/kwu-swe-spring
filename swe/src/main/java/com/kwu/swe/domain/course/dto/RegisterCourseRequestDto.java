package com.kwu.swe.domain.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCourseRequestDto {
    private String courseName;
    private String courseNumber;
    //TODO 학점 리미트 제한
    private int score;
}
