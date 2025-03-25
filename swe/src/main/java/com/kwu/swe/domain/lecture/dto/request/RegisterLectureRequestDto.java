package com.kwu.swe.domain.lecture.dto.request;

import lombok.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLectureRequestDto {
    private Long courseId;
    private int sizeLimit;
    private Year year;
    private String semester;
    private String lectureStatus;

    @Builder.Default
    private Map<Long,String> lectureLocationAndTime = new HashMap<>();
}
