package com.kwu.swe.domain.user.service;


import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import com.kwu.swe.domain.user.dto.EditUserInfoRequestDto;
import com.kwu.swe.domain.user.dto.RegisterUserRequestDto;
import com.kwu.swe.domain.user.entity.*;
import com.kwu.swe.domain.user.repository.LectureAssistantRepository;
import com.kwu.swe.domain.user.repository.LectureStudentRepository;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService{

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final LectureStudentRepository lectureStudentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long registerUser(RegisterUserRequestDto dto, Role role) {
        User user = User.builder()
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .code(dto.getCode())
                .phoneNumber(dto.getPhoneNumber())
                .role(role)
                .build();
        return userRepository.save(user).getId();
    }

    @Override
    public Long updateUserInfo(String code, EditUserInfoRequestDto dto) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        user.updateInfo(passwordEncoder.encode(dto.getPassword()), dto.getPhoneNumber());
        return user.getId();
    }

    @Override
    public Long updatePassword(String code, String password) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        user.updatePassword(passwordEncoder.encode(password));
        return user.getId();
    }

    @Override
    public Long assignGrade(String code, Long studentId, Long lectureId, Grade grade) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_NOT_FOUND));

        if (!lecture.getProfessor().getId().equals(user.getId())) {
            throw new GeneralException(ErrorStatus.ONLY_TOUCHED_BY_PROFESSOR);
        }

        LectureStudent lectureStudent = lectureStudentRepository.findByStudentIdAndLectureId(studentId, lectureId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.LECTURE_NOT_FOUND));
        lectureStudent.assignGrade(grade);

        return lectureStudent.getId();
    }

}
