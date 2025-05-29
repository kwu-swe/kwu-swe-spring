package com.kwu.swe.domain.announcement.controller;

import com.kwu.swe.domain.announcement.dto.AnnouncementRequestDto;
import com.kwu.swe.domain.announcement.dto.AnnouncementResponseDto;
import com.kwu.swe.domain.announcement.dto.AnnouncementSummaryDto;
import com.kwu.swe.domain.announcement.entity.Announcement;
import com.kwu.swe.domain.announcement.service.AnnouncementCommandService;
import com.kwu.swe.domain.announcement.service.AnnouncementQueryService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementApiController {

    private final AnnouncementCommandService announcementCommandService;
    private final AnnouncementQueryService announcementQueryService;

    @PostMapping("/lectures/{lectureId}")
    public ApiResponseDto<Long> registerAnnouncement(@PathVariable Long lectureId,
                                                     @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
                                                     @RequestBody AnnouncementRequestDto announcementRequestDto) {
        return ApiResponseDto.onSuccess(announcementCommandService.registerAnnouncement(lectureId, userDetails.getUsername(), announcementRequestDto));
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


    @GetMapping("/{announcementId}")
    public ApiResponseDto<AnnouncementResponseDto> getSpecificAnnouncement(@PathVariable Long announcementId) {
        Announcement specificAnnouncement = announcementQueryService.getSpecificAnnouncement(announcementId);
        AnnouncementResponseDto build = AnnouncementResponseDto.builder()
                .announcementId(announcementId)
                .encodedFiles(
                        specificAnnouncement.getAnnouncementFileList().stream()
                                .map(
                                        announcementFile -> announcementFile.getEncodedURL()
                                ).toList()
                )
                .content(specificAnnouncement.getContent())
                .writer(specificAnnouncement.getLecture().getProfessor().getName())
                .title(specificAnnouncement.getTitle())
                .build();
        return ApiResponseDto.onSuccess(build);
    }
}
