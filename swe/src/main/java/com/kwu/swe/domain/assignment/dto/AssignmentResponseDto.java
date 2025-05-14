package com.kwu.swe.domain.assignment.dto;

import com.kwu.swe.domain.assignment.entity.AssignmentFile;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AssignmentResponseDto {
    private String title;  // 과제 제목
    private String content;  // 과제 내용
    private int dueDateAfterDays; // 생성일 기준 며칠 후 마감일
    private List<String> encodedFiles;
}
