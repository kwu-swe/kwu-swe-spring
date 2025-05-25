package com.kwu.swe.domain.lecture_location.service;

import com.kwu.swe.domain.lecture_location.entity.LectureLocation;
import com.kwu.swe.domain.lecture_location.repository.LectureLocationRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureLocationQueryServiceImpl implements LectureLocationQueryService{

    private final LectureLocationRepository lectureLocationRepository;

    @Override
    public List<LectureLocation> getAllLectureLocation() {
        return lectureLocationRepository.findAll();
    }

    @Override
    public LectureLocation getLectureLocationById(Long lectureLocationId) {
        return lectureLocationRepository.findById(lectureLocationId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_LOCATION_NOT_FOUND));
    }
}
