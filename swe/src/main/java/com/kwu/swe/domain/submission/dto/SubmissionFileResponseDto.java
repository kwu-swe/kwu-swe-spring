package com.kwu.swe.domain.submission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionFileResponseDto {
    private String fileName;
    private String encodedResult;
}
