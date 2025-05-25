package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.course.entity.Course;
import com.kwu.swe.domain.course.repository.CourseRepository;
import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;
import com.kwu.swe.domain.lecture.entity.*;
import com.kwu.swe.domain.lecture_location.repository.LectureLocationRepository;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.lecture_schedule.entity.ClassTime;
import com.kwu.swe.domain.lecture_schedule.entity.LectureSchedule;
import com.kwu.swe.domain.user.entity.LectureAssistant;
import com.kwu.swe.domain.user.entity.LectureStudent;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.LectureAssistantRepository;
import com.kwu.swe.domain.user.repository.LectureStudentRepository;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.global.util.EnumConvertUtil;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LectureCommandServiceImpl implements LectureCommandService{

    private final LectureRepository lectureRepository;
    private final LectureLocationRepository lectureLocationRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final LectureStudentRepository lectureStudentRepository;
    private final LectureAssistantRepository lectureAssistantRepository;

    /**
     * 1. lecture의 course 선택
     * 2. lecture의 professor 선택
     * 2. lecture의 semester 선택
     * 3. lecture의 schedule 생성
     * @return
     * 학기에 따른 생성 제약을 두지 않고 최대한 자율성을 줌(동작이 보이는게 중요함)
     */
    @Override
    public Long registerLecture(String code, RegisterLectureRequestDto dto) {
        //lecture professor 조회
        User professor = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        //유저 권한 확인(only professor)
        if(!professor.getRole().equals(Role.ROLE_PROFESSOR)){
            throw new GeneralException(ErrorStatus.ONLY_TOUCHED_BY_PROFESSOR);
        }
        //lecture course 조회
        Course findCourse = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.COURSE_NOT_FOUND));
        //lecture entity 생성
        Lecture newLecture = Lecture.builder()
                .lectureStatus(EnumConvertUtil.convert(LectureStatus.class, dto.getLectureStatus()))
                .semester(EnumConvertUtil.convert(Semester.class, dto.getSemester()))
                .sizeLimit(dto.getSizeLimit())
                .year(Year.of(dto.getYear()))
                .course(findCourse)
                .professor(professor)
                .build();
        //lecture schedule link
        //cascade.PERSIST를 통해 lecture save시 데이터베이스에 자동 입력
        for (Map.Entry<String, Long> entry : dto.getLectureTimeAndLocationId().entrySet()) {
            LectureSchedule.builder()
                    .lectureLocation(
                            lectureLocationRepository.findById(entry.getValue())
                                    .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_LOCATION_NOT_FOUND))
                    )
                    .classTime(EnumConvertUtil.convert(ClassTime.class, entry.getKey()))
                    .lecture(newLecture)
                    .build().linkInList(newLecture);
        }
        return lectureRepository.save(newLecture).getId();
    }

    @Override
    public Long registerCourse(String code, Long lectureId) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException((ErrorStatus.LECTURE_NOT_FOUND)));
        if (!user.getRole().equals(Role.ROLE_STUDENT)) {
            throw new GeneralException(ErrorStatus.ONLY_TOUCHED_BY_STUDENT);
        }
        LectureStudent lectureStudent = LectureStudent.builder()
                .student(user)
                .lecture(lecture)
                .build();
        return lectureStudentRepository.save(lectureStudent).getId();
    }

    @Override
    public Long registerAssistantOfLecture(String professorCode, String assistantCode, Long lectureId) {
        User professor = userRepository.findUserByCode(professorCode)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException((ErrorStatus.LECTURE_NOT_FOUND)));
        if (!lecture.getProfessor().equals(professor)) {
            throw new GeneralException(ErrorStatus.NOT_MATCH_PROFESSOR);
        }

        User user = userRepository.findUserByCode(assistantCode)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        LectureAssistant lectureAssistant = LectureAssistant.builder()
                .user(user)
                .lecture(lecture)
                .build();
        return lectureAssistantRepository.save(lectureAssistant).getId();
    }
}
