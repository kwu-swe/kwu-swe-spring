package com.kwu.swe.domain.submission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionResponseDto {
    private Long id;
    private String title;
    private String content;
}
