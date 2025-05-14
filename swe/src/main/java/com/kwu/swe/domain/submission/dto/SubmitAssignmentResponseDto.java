package com.kwu.swe.domain.submission.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SubmitAssignmentResponseDto {

    private String title;
    private String message;
    private boolean success;
    private Long submissionId; // 제출된 과제의 ID
    private String fileURL;
}
