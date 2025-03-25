package com.kwu.swe.domain.lecture.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLectureRequestDto {
    private Long courseId;
    private int sizeLimit;
    private Year year;
    private String semester;
    private String lectureStatus;
}
