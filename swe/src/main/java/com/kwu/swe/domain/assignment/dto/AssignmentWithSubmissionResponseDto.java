package com.kwu.swe.domain.assignment.dto;

import com.kwu.swe.domain.auditing.entity.BaseTimeEntity;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AssignmentWithSubmissionResponseDto {
    private Long assignmentId;
    private String title;  // 과제 제목
    private String content;
    private LocalDateTime dueDate; // 생성일 기준 며칠 후 마감일
    private LocalDateTime createdAt;
    private List<String> encodedFiles;
    private SubmitAssignmentResponseDto submitAssignmentResponseDto;
}
