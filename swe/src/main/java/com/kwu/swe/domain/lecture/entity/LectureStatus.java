package com.kwu.swe.domain.lecture.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LectureStatus implements KeyedEnum {

    BEFORE("BEFORE"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private final String key;
}
