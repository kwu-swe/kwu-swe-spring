package com.kwu.swe.domain.announcement.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class AnnouncementRequestDto {

    private String title;
    private String content;
}
