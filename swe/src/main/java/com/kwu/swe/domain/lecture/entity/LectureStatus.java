package com.kwu.swe.domain.lecture.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LectureStatus implements KeyedEnum {

    BEFORE("강의 전"),
    IN_PROGRESS("강의 중"),
    COMPLETED("강의 완료");

    private final String key;
}
