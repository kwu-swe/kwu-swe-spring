package com.kwu.swe.domain.announcement.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class AnnouncementSummaryDto {
    private Long announcementId;
    private String title;
    private LocalDateTime createdAt;

}
