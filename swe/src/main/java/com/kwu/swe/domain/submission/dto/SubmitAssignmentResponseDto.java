package com.kwu.swe.domain.submission.dto;

import lombok.Builder;

@Builder
public class SubmitAssignmentResponseDto {

    private String message;
    private boolean success;
    private Long submissionId; // 제출된 과제의 ID
}
