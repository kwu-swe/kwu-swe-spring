package com.kwu.swe.domain.lecture_plan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPlanRequestDto {
    private String description;
    private String goal;
}
