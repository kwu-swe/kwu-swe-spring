package com.kwu.swe.domain.lecture_plan.dto;

import com.kwu.swe.domain.lecture.entity.LectureStatus;
import com.kwu.swe.domain.lecture.entity.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Year;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponseDto {

    private Long id;
    private int sizeLimit;
    private Year year;
    private LectureStatus lectureStatus;
    private Semester semester;

    private Long courseId;
    private String courseName;

    private Long professorId;
    private String professorName;
}
