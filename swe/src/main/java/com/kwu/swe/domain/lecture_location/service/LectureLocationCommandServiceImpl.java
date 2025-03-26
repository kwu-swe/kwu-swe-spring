package com.kwu.swe.domain.lecture_location.service;

import com.kwu.swe.domain.lecture_location.dto.RegisterLocationRequestDto;
import com.kwu.swe.domain.lecture_location.entity.LectureLocation;
import com.kwu.swe.domain.lecture_location.repository.LectureLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureLocationCommandServiceImpl implements LectureLocationCommandService{

    private final LectureLocationRepository lectureLocationRepository;
    @Override
    public Long registerLocation(RegisterLocationRequestDto dto) {
        LectureLocation newLocation = LectureLocation.builder()
                .location(dto.getLocation())
                .sizeLimit(dto.getSizeLimit())
                .build();
        return lectureLocationRepository.save(newLocation).getId();
    }
}
