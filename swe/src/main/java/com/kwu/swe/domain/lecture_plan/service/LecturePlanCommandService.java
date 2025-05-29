package com.kwu.swe.domain.lecture_plan.service;

import com.kwu.swe.domain.lecture_plan.dto.RegisterPlanRequestDto;

public interface LecturePlanCommandService {
    Long registerLocation(RegisterPlanRequestDto dto, String code);
}
