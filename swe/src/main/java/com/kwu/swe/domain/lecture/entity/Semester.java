package com.kwu.swe.domain.lecture.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Semester implements KeyedEnum {

    FIRST_SEMESTER("FIRST_SEMESTER"),
    SECOND_SEMESTER("SECOND_SEMESTER"),
    SUMMER("SUMMER"),
    WINTER("WINTER");

    private final String key;
}
