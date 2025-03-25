package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.entity.LectureStatus;
import com.kwu.swe.domain.lecture.entity.Semester;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.global.util.EnumConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureCommandServiceImpl implements LectureCommandService{

    private final LectureRepository lectureRepository;

    /**
     * 1. lecture의 course 선택
     * 2. lecture의 professor 선택
     * 2. lecture의 semester 선택
     * 3. lecture의 schedule 생성
     * @return
     */
    @Override
    public Long registerLecture(RegisterLectureRequestDto dto) {
        //lecture professor 조회
        //lecture course 조회
        //lecture entity 생성
        Lecture newLecture = Lecture.builder()
                .lectureStatus(EnumConvertUtil.convert(LectureStatus.class, dto.getLectureStatus()))
                .semester(EnumConvertUtil.convert(Semester.class, dto.getSemester()))
                .sizeLimit(dto.getSizeLimit())
                .year(dto.getYear())
//                .course()
//                .professor()
                .build();

        return lectureRepository.save(newLecture).getId();
    }
}
