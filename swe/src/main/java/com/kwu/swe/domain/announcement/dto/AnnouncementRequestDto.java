package com.kwu.swe.domain.announcement.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Jacksonized
public class AnnouncementRequestDto {

    private String title;
    private String content;
    @Builder.Default
    private List<String> encodedFiles = new ArrayList<>();
}
