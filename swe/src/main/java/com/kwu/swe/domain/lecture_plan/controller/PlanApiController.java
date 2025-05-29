package com.kwu.swe.domain.lecture_plan.controller;

import com.kwu.swe.domain.announcement.dto.AnnouncementSummaryDto;
import com.kwu.swe.domain.announcement.entity.Announcement;
import com.kwu.swe.domain.lecture_location.dto.RegisterLocationRequestDto;
import com.kwu.swe.domain.lecture_plan.dto.RegisterPlanRequestDto;
import com.kwu.swe.domain.lecture_plan.service.LecturePlanCommandService;
import com.kwu.swe.domain.lecture_plan.service.LecturePlanCommandServiceImpl;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanApiController {

    private final LecturePlanCommandService lecturePlanCommandService;

    @PostMapping("/lectures/{lectureId}")
    public ApiResponseDto<Long> registerPlan(@PathVariable Long lectureId,
                                             @RequestBody RegisterPlanRequestDto dto,
                                             @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponseDto.onSuccess(lecturePlanCommandService.registerPlan(dto, userDetails.getUsername(), lectureId));
    }

    @GetMapping("/lectures/{lectureId}")
    public ApiResponseDto<List<AnnouncementSummaryDto>> getAnnouncementsByLecture(@PathVariable Long lectureId) {
        List<Announcement> announcements = announcementQueryService.getAnnouncementByLectureId(lectureId);
        List<AnnouncementSummaryDto> result = announcements.stream().map(announcement ->
                AnnouncementSummaryDto.builder()
                        .announcementId(announcement.getId())
                        .title(announcement.getTitle())
                        .createdAt(announcement.getCreatedAt())
                        .build()
        ).toList();
        return ApiResponseDto.onSuccess(result);
    }
}
