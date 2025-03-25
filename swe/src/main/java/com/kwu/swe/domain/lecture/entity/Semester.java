package com.kwu.swe.domain.lecture.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Semester {

    FIRST_SEMESTER("1학기"),
    SECOND_SEMESTER("2학기"),
    SUMMER("계절학기(하)"),
    WINTER("게절학기(동)");

    private final String key;
}
