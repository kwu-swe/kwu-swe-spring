package com.kwu.swe.domain.lecture_plan.service;

import com.kwu.swe.domain.lecture_plan.dto.RegisterPlanRequestDto;

public interface LecturePlanCommandService {
    Long registerPlan(RegisterPlanRequestDto dto, String code, Long lectureId);
}
