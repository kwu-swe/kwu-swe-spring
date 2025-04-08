package com.kwu.swe.domain.lecture_location.service;

import com.kwu.swe.domain.lecture_location.entity.LectureLocation;
import com.kwu.swe.domain.lecture_location.repository.LectureLocationRepository;
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
}
