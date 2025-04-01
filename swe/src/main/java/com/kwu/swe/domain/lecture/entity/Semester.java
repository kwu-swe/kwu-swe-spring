package com.kwu.swe.domain.lecture.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Semester implements KeyedEnum {

    FIRST_SEMESTER("1학기"),
    SECOND_SEMESTER("2학기"),
    SUMMER("계절학기(하)"),
    WINTER("게절학기(동)");

    private final String key;
}
