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

    @Override
    public Long registerUser(RegisterUserRequestDto dto, Role role) {
        User user = User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .code(dto.getStudentNumber())
                .phoneNumber(dto.getPhoneNumber())
                .role(role)
                .build();
        return userRepository.save(user).getId();
    }

    @Override
    public Long updateUserInfo(String studentNumber, EditUserInfoRequestDto dto) {
        User user = userRepository.findUserByCode(studentNumber)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        user.updateInfo(dto);
        return user.getId();
    }


}
