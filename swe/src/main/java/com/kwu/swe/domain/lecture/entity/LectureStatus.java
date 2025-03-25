package com.kwu.swe.domain.lecture.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LectureStatus {

    BEFORE("강의 전"),
    IN_PROGRESS("강의 중"),
    COMPLETED("강의 완료");

    private final String key;
}
