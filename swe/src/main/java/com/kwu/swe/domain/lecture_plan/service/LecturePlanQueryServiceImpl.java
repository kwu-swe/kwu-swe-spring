package com.kwu.swe.domain.lecture_plan.service;

import com.kwu.swe.domain.assignment.dto.AssignmentWithSubmissionResponseDto;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.lecture_plan.dto.PlanResponseDto;
import com.kwu.swe.domain.lecture_plan.entity.LecturePlan;
import com.kwu.swe.domain.lecture_plan.repository.LecturePlanRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LecturePlanQueryServiceImpl implements LecturePlanQueryService {

    private final LecturePlanRepository lecturePlanRepository;
    private final LectureRepository lectureRepository;

    @Override
    public PlanResponseDto getPlanByLectureId(Long lectureId) {

        LecturePlan lecturePlan = lecturePlanRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_PLAN_NOT_FOUND));

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_NOT_FOUND));

        PlanResponseDto responseDto = PlanResponseDto.builder()
                .id(lecturePlan.getId())
                .sizeLimit(lecture.getSizeLimit())
                .year(lecture.getYear())
                .lectureStatus(lecture.getLectureStatus())
                .semester(lecture.getSemester())
                .courseId(lecture.getCourse().getId())
                .courseName(lecture.getCourse().getCourseName())
                .professorId(lecture.getProfessor().getId())
                .professorName(lecture.getProfessor().getName())
                .build();

        return responseDto;
    }
}
