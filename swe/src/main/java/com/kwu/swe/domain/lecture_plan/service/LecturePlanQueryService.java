package com.kwu.swe.domain.lecture_plan.service;

import com.kwu.swe.domain.lecture_plan.dto.PlanResponseDto;

public interface LecturePlanQueryService {
    PlanResponseDto getPlanByLectureId(Long lectureId);
}
