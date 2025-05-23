package com.kwu.swe.domain.material.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@Jacksonized
public class MaterialSummaryDto {

    private Long materialId;
    private String title;
    private LocalDateTime createdAt;
}
