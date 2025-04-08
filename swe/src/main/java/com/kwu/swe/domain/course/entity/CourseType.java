package com.kwu.swe.domain.course.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseType implements KeyedEnum {
    MAJOR_REQUIRED("전공필수"),
    MAJOR_ELECTIVE("전공선택"),
    GENERAL_REQUIRED("교양필수"),
    GENERAL_ELECTIVE("교양선택");

    private final String key;

}
