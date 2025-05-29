package com.kwu.swe.domain.lecture_plan.controller;

import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.lecture_plan.dto.PlanResponseDto;
import com.kwu.swe.domain.lecture_plan.dto.RegisterPlanRequestDto;
import com.kwu.swe.domain.lecture_plan.entity.LecturePlan;
import com.kwu.swe.domain.lecture_plan.service.LecturePlanCommandService;
import com.kwu.swe.domain.lecture_plan.service.LecturePlanQueryService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanApiController {

    private final LecturePlanCommandService lecturePlanCommandService;
    private final LecturePlanQueryService lecturePlanQueryService;

    @PostMapping("/lectures/{lectureId}")
    public ApiResponseDto<Long> registerPlan(@PathVariable Long lectureId,
                                             @RequestBody RegisterPlanRequestDto dto,
                                             @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponseDto.onSuccess(lecturePlanCommandService.registerPlan(dto, userDetails.getUsername(), lectureId));
    }

    @GetMapping("/lectures/{lectureId}")
    public ApiResponseDto<PlanResponseDto> getPlanByLecture(@PathVariable Long lectureId) {

        PlanResponseDto planResponseDto = lecturePlanQueryService.getPlanByLectureId(lectureId);

        return ApiResponseDto.onSuccess(planResponseDto);
    }
}
