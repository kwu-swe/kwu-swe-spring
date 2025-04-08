package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureQueryServiceImpl implements LectureQueryService{

    private final LectureRepository lectureRepository;

    @Override
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }
}
