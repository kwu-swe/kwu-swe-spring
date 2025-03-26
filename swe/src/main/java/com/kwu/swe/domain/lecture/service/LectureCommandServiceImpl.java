package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.course.entity.Course;
import com.kwu.swe.domain.course.repository.CourseRepository;
import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;
import com.kwu.swe.domain.lecture.entity.*;
import com.kwu.swe.domain.lecture_location.repository.LectureLocationRepository;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.lecture_schedule.entity.ClassTime;
import com.kwu.swe.domain.lecture_schedule.entity.LectureSchedule;
import com.kwu.swe.global.util.EnumConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class LectureCommandServiceImpl implements LectureCommandService{

    private final LectureRepository lectureRepository;
    private final LectureLocationRepository lectureLocationRepository;
    private final CourseRepository courseRepository;

    /**
     * 1. lecture의 course 선택
     * 2. lecture의 professor 선택
     * 2. lecture의 semester 선택
     * 3. lecture의 schedule 생성
     * @return
     * 학기에 따른 생성 제약을 두지 않고 최대한 자율성을 줌(동작이 보이는게 중요함)
     */
    @Override
    public Long registerLecture(RegisterLectureRequestDto dto) {
        //TODO lecture professor 조회
        //lecture course 조회
        Course findCourse = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("not found course"));
        //lecture entity 생성
        Lecture newLecture = Lecture.builder()
                .lectureStatus(EnumConvertUtil.convert(LectureStatus.class, dto.getLectureStatus()))
                .semester(EnumConvertUtil.convert(Semester.class, dto.getSemester()))
                .sizeLimit(dto.getSizeLimit())
                .year(dto.getYear())
                .course(findCourse)
//                .professor()
                .build();
        //lecture schedule link
        //cascade.PERSIST를 통해 lecture save시 데이터베이스에 자동 입력
        for (Map.Entry<Long, String> entry : dto.getLectureLocationAndTime().entrySet()) {
            LectureSchedule.builder()
                    .lectureLocation(
                            lectureLocationRepository.findById(entry.getKey())
                                    .orElseThrow(() -> new RuntimeException("cannot find lecture location"))
                    )
                    .classTime(EnumConvertUtil.convert(ClassTime.class, entry.getValue()))
                    .lecture(newLecture)
                    .build().linkInList(newLecture);
        }
        return lectureRepository.save(newLecture).getId();
    }
}
