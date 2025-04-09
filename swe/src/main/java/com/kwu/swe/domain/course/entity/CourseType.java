package com.kwu.swe.domain.course.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseType implements KeyedEnum {
    MAJOR_REQUIRED("MAJOR_REQUIRED"),
    MAJOR_ELECTIVE("MAJOR_ELECTIVE"),
    GENERAL_REQUIRED("GENERAL_REQUIRED"),
    GENERAL_ELECTIVE("GENERAL_ELECTIVE");

    private final String key;

}
