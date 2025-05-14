package com.kwu.swe.domain.material.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Jacksonized
public class MaterialResponseDto {
    private Long materialId;
    private String title;
    private String content;
    private String writer;
    @Builder.Default
    private List<String> encodedFiles = new ArrayList<>();
}
