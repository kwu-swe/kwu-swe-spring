package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.user.entity.LectureStudent;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.LectureStudentRepository;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Lecture> getStudentLectures(String studentNumber) {
        User student = userRepository.findUserByStudentNumber(studentNumber)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        //TODO validate student
        List<LectureStudent> lectureStudents = lectureStudentRepository.findByStudent(student);

        return lectureStudents.stream()
                .map(LectureStudent::getLecture)
                .toList();
    }
}
