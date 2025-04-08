package com.kwu.swe.domain.user.service;

import com.kwu.swe.domain.user.dto.EditUserInfoRequestDto;
import com.kwu.swe.domain.user.dto.RegisterUserRequestDto;

public interface UserCommandService {

    Long registerUser(RegisterUserRequestDto dto);

    Long updateUserInfo(String studentNumber, EditUserInfoRequestDto dto);

    Long registerCourse(String studentNumber, Long lectureId);

    Long registerAssistantOfLecture(String studentNumber, Long lectureId);
}
