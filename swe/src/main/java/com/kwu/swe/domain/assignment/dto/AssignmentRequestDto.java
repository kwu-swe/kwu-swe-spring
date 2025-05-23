package com.kwu.swe.domain.assignment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AssignmentRequestDto {
    private String title;  // 과제 제목
    private String content;  // 과제 내용
    private int dueDateAfterDays; // 생성일 기준 며칠 후 마감일
    private LocalDateTime dueDate;
    private List<String> encodedFiles;
}
