package com.kwu.swe.domain.lecture_location.service;

import com.kwu.swe.domain.lecture_location.dto.RegisterLocationRequestDto;

public interface LectureLocationCommandService {

    Long registerLocation(RegisterLocationRequestDto dto);
}
