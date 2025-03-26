package com.kwu.swe.domain.lecture_location.controller;

import com.kwu.swe.domain.lecture_location.dto.RegisterLocationRequestDto;
import com.kwu.swe.domain.lecture_location.service.LectureLocationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationApiController {

    private final LectureLocationCommandService lectureLocationCommandService;

    @PostMapping
    public Long registerLocation(@RequestBody RegisterLocationRequestDto dto) {
        return lectureLocationCommandService.registerLocation(dto);
    }
}
