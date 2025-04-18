package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;

public interface LectureCommandService {

    //TODO add arguments User entity
    Long registerLecture(String studentNumber, RegisterLectureRequestDto dto);

    Long registerCourse(String studentNumber, Long lectureId);

    Long registerAssistantOfLecture(String professorNumber, String studentNumber, Long lectureId);
}
