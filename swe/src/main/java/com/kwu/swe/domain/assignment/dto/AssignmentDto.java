package com.kwu.swe.domain.assignment.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class AssignmentDto {
    private String title;  // 과제 제목
    private String content;  // 과제 내용
    private LocalDateTime dueDate;  // 마감일
    private LocalDateTime extendedDueDate;  // 추가 마감일
}
