package com.kwu.swe.domain.assignment.dto;

import lombok.Getter;
import lombok.Builder;
import java.time.LocalDateTime;

@Getter
@Builder
public class AssignmentDto {

//    private Long lectureId;  // 과제에 해당하는 lecture ID
    private String title;
    private String content;
    private LocalDateTime dueDate;
    private LocalDateTime extendedDueDate;
    private boolean allowSubmission;
    private boolean isPublic;
}
