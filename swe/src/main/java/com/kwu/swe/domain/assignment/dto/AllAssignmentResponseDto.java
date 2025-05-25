package com.kwu.swe.domain.assignment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AllAssignmentResponseDto {
    private Long assignmentId;
    private String title;
    private LocalDateTime dueDate;
}
