package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;

public interface LectureCommandService {

    //TODO add arguments User entity
    Long registerLecture(String code, RegisterLectureRequestDto dto);

    Long registerCourse(String code, Long lectureId);

    Long registerAssistantOfLecture(String professorCode, String assistantCode, Long lectureId);
}
