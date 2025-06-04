package com.kwu.swe.domain.user.service;

import com.kwu.swe.domain.user.dto.EditUserInfoRequestDto;
import com.kwu.swe.domain.user.dto.RegisterUserRequestDto;
import com.kwu.swe.domain.user.entity.Grade;
import com.kwu.swe.domain.user.entity.Role;

public interface UserCommandService {

    Long registerUser(RegisterUserRequestDto dto, Role role);

    Long updateUserInfo(String code, EditUserInfoRequestDto dto);

    Long updatePassword(String code, String password);

    Long assignGrade(String code, Long studentId, Long lectureId, Grade grade);


}
