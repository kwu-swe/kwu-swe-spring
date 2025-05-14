package com.kwu.swe.domain.lecture_location.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LocationResponseDto {
    private Long locationId;
    private String locationName;
    private int sizeLimit;
    private LocalDateTime createdAt;
}
