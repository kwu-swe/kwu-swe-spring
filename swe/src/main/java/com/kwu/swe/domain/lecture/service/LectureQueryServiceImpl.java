package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.user.entity.Grade;
import com.kwu.swe.domain.user.entity.LectureStudent;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.LectureStudentRepository;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureQueryServiceImpl implements LectureQueryService{

    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;
    private final LectureStudentRepository lectureStudentRepository;

    @Override
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    @Override
    public List<Lecture> getStudentLectures(String code) {
        log.info("user not found");
        User student = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        log.info("user found");
        //TODO validate student
        List<LectureStudent> lectureStudents = lectureStudentRepository.findByStudent(student);
        log.info("lectureStudents.size() : {}", lectureStudents.size());

        return lectureStudents.stream()
                .map(LectureStudent::getLecture)
                .toList();
    }

    @Override
    public List<LectureStudent> getGradesOfLecture(String code, Long lectureId) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_NOT_FOUND));

        if (!lecture.getProfessor().getId().equals(user.getId())) {
            throw new GeneralException(ErrorStatus.ONLY_TOUCHED_BY_PROFESSOR);
        }
        return lectureStudentRepository.findByLecture(lecture);
    }

    @Override
    public LectureStudent getStudentGrade(String code, Long lectureId) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        return lectureStudentRepository
                .findByStudentIdAndLectureId(user.getId(), lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_STUDENT_NOT_FOUND));
    }
}
