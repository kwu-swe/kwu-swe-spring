package com.kwu.swe.domain.submission.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SubmitAssignmentResponseDto {
    private Long submissionId;
    private String title;
    private String content;
    private List<String> encodedFiles;
}
