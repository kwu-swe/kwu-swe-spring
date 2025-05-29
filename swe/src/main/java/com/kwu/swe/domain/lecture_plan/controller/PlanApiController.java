package com.kwu.swe.domain.lecture_plan.controller;

import com.kwu.swe.domain.lecture_location.dto.RegisterLocationRequestDto;
import com.kwu.swe.domain.lecture_plan.dto.RegisterPlanRequestDto;
import com.kwu.swe.domain.lecture_plan.service.LecturePlanCommandService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanApiController {

    private final LecturePlanCommandService lecturePlanCommandService;

    @PostMapping
    public ApiResponseDto<Long> registerPlan(@RequestBody RegisterPlanRequestDto dto,
                                             @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponseDto.onSuccess(lecturePlanCommandService.registerLocation(dto, userDetails.getUsername()));
    }
}
