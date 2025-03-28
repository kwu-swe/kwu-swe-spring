package com.kwu.swe.domain.submission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDto {
    private Long assignmentId;
    private Long studentId;
    private String status; // 예: "submitted", "not_submitted"
}
