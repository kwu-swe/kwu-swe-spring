package com.kwu.swe.domain.lecture.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClassTime {
    MON_1("월요일 1교시"),
    MON_2("월요일 2교시"),
    MON_3("월요일 3교시"),
    MON_4("월요일 4교시"),
    MON_5("월요일 5교시"),
    MON_6("월요일 6교시"),
    MON_7("월요일 7교시"),
    MON_8("월요일 8교시"),

    TUE_1("화요일 1교시"),
    TUE_2("화요일 2교시"),
    TUE_3("화요일 3교시"),
    TUE_4("화요일 4교시"),
    TUE_5("화요일 5교시"),
    TUE_6("화요일 6교시"),
    TUE_7("화요일 7교시"),
    TUE_8("화요일 8교시"),

    WED_1("수요일 1교시"),
    WED_2("수요일 2교시"),
    WED_3("수요일 3교시"),
    WED_4("수요일 4교시"),
    WED_5("수요일 5교시"),
    WED_6("수요일 6교시"),
    WED_7("수요일 7교시"),
    WED_8("수요일 8교시"),

    THU_1("목요일 1교시"),
    THU_2("목요일 2교시"),
    THU_3("목요일 3교시"),
    THU_4("목요일 4교시"),
    THU_5("목요일 5교시"),
    THU_6("목요일 6교시"),
    THU_7("목요일 7교시"),
    THU_8("목요일 8교시"),

    FRI_1("금요일 1교시"),
    FRI_2("금요일 2교시"),
    FRI_3("금요일 3교시"),
    FRI_4("금요일 4교시"),
    FRI_5("금요일 5교시"),
    FRI_6("금요일 6교시"),
    FRI_7("금요일 7교시"),
    FRI_8("금요일 8교시"),

    SAT_1("토요일 1교시"),
    SAT_2("토요일 2교시"),
    SAT_3("토요일 3교시"),
    SAT_4("토요일 4교시"),
    SAT_5("토요일 5교시"),
    SAT_6("토요일 6교시"),
    SAT_7("토요일 7교시"),
    SAT_8("토요일 8교시");

    private final String key;
}
