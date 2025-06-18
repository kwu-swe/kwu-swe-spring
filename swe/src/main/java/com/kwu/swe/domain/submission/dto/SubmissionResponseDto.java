package com.kwu.swe.domain.submission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponseDto {
    private Long submissionId;
    private String title;
    private String content;
    private List<String> encodedFiles;
}
