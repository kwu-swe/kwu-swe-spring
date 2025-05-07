package com.kwu.swe.domain.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Builder
public class AssignmentDto {
    private String title;  // 과제 제목
    private String content;  // 과제 내용
    private int dueDateAfterDays; // 생성일 기준 며칠 후 마감일
}
