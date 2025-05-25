package com.kwu.swe.domain.assignment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AssignmentRevisedRespondDto {
    private String title;  // 과제 제목
    private String content;  // 과제 내용
    private LocalDateTime dueDate; // 생성일 기준 며칠 후 마감일
    private List<String> encodedFiles;
}
