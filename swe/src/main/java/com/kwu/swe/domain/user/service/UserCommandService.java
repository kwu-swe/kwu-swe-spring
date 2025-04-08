package com.kwu.swe.domain.user.service;

public interface UserCommandService {

    Long registerUser(RegisterUserRequestDto dto);

    Long updateUserInfo(EditUserInfoRequestDto dto);

    Long registerCourse(String studentNumber, Long lectureId);

    Long addLecture(String studentNumber, Long lectureId);
}
