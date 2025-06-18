package com.kwu.swe.domain.lecture_location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterLocationRequestDto {

    private String locationName;
    private int sizeLimit;
}
