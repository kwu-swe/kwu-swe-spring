package com.kwu.swe.domain.lecture_location.controller;

import com.kwu.swe.domain.lecture_location.dto.LocationResponseDto;
import com.kwu.swe.domain.lecture_location.dto.RegisterLocationRequestDto;
import com.kwu.swe.domain.lecture_location.entity.LectureLocation;
import com.kwu.swe.domain.lecture_location.service.LectureLocationCommandService;
import com.kwu.swe.domain.lecture_location.service.LectureLocationQueryService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationApiController {

    private final LectureLocationCommandService lectureLocationCommandService;
    private final LectureLocationQueryService lectureLocationQueryService;

    @PostMapping
    public ApiResponseDto<Long> registerLocation(@RequestBody RegisterLocationRequestDto dto) {
        return ApiResponseDto.onSuccess(lectureLocationCommandService.registerLocation(dto));
    }

    @GetMapping
    public ApiResponseDto<List<LocationResponseDto>> getAllLocation() {
        List<LectureLocation> allLocation = lectureLocationQueryService.getAllLectureLocation();
        List<LocationResponseDto> result = allLocation.stream()
                .map(l -> LocationResponseDto.builder()
                        .locationName(l.getLocation())
                        .sizeLimit(l.getSizeLimit())
                        .locationId(l.getId())
                        .createdAt(l.getCreatedAt())
                        .build())
                .toList();
        return ApiResponseDto.onSuccess(result);
    }

    @GetMapping("/{locationId}")
    public ApiResponseDto<LocationResponseDto> getLocationById(@PathVariable Long locationId) {
        LectureLocation lectureLocation = lectureLocationQueryService.getLectureLocationById(locationId);
        LocationResponseDto result = LocationResponseDto.builder()
                .locationName(lectureLocation.getLocation())
                .sizeLimit(lectureLocation.getSizeLimit())
                .locationId(lectureLocation.getId())
                .createdAt(lectureLocation.getCreatedAt())
                .build();
        return ApiResponseDto.onSuccess(result);
    }
}
