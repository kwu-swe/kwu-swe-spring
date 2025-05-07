package com.kwu.swe.domain.announcement.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnnouncementResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public AnnouncementResponseDto(Long id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
