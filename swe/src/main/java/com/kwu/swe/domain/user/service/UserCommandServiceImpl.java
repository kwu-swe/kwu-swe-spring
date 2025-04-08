package com.kwu.swe.domain.user.service;


import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.user.dto.EditUserInfoRequestDto;
import com.kwu.swe.domain.user.dto.RegisterUserRequestDto;
import com.kwu.swe.domain.user.entity.LectureAssistant;
import com.kwu.swe.domain.user.entity.LectureStudent;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.LectureAssistantRepository;
import com.kwu.swe.domain.user.repository.LectureStudentRepository;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final LectureStudentRepository lectureStudentRepository;
    private final LectureAssistantRepository lectureAssistantRepository;

    @Override
    public Long registerUser(RegisterUserRequestDto dto, Role role) {
        User user = User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .studentNumber(dto.getStudentNumber())
                .phoneNumber(dto.getPhoneNumber())
                .role(role)
                .build();
        return userRepository.save(user).getId();
    }

    @Override
    public Long updateUserInfo(String studentNumber, EditUserInfoRequestDto dto) {
        User user = userRepository.findUserByStudentNumber(studentNumber)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        user.updateInfo(dto);
        return user.getId();
    }

    @Override
    public Long registerCourse(String studentNumber, Long lectureId) {
        User user = userRepository.findUserByStudentNumber(studentNumber)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException((ErrorStatus.LECTURE_NOT_FOUND)));
        LectureStudent lectureStudent = LectureStudent.builder()
                .user(user)
                .lecture(lecture)
                .build();
        return lectureStudentRepository.save(lectureStudent).getId();
    }

    @Override
    public Long registerAssistantOfLecture(String professorNumber, String studentNumber, Long lectureId) {
        User professor = userRepository.findUserByStudentNumber(professorNumber)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException((ErrorStatus.LECTURE_NOT_FOUND)));
        if (!lecture.getProfessor().equals(professor)) {
            throw new GeneralException(ErrorStatus.NOT_MATCH_PROFESSOR);
        }

        User user = userRepository.findUserByStudentNumber(studentNumber)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        LectureAssistant lectureAssistant = LectureAssistant.builder()
                .user(user)
                .lecture(lecture)
                .build();
        return lectureAssistantRepository.save(lectureAssistant).getId();
    }
}
