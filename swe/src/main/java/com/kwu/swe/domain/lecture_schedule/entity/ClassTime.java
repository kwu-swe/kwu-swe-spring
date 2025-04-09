package com.kwu.swe.domain.lecture_schedule.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClassTime implements KeyedEnum {
    MON_1("MON_1"),
    MON_2("MON_2"),
    MON_3("MON_3"),
    MON_4("MON_4"),
    MON_5("MON_5"),
    MON_6("MON_6"),
    MON_7("MON_7"),
    MON_8("MON_8"),

    TUE_1("TUE_1"),
    TUE_2("TUE_2"),
    TUE_3("TUE_3"),
    TUE_4("TUE_4"),
    TUE_5("TUE_5"),
    TUE_6("TUE_6"),
    TUE_7("TUE_7"),
    TUE_8("TUE_8"),

    WED_1("WED_1"),
    WED_2("WED_2"),
    WED_3("WED_3"),
    WED_4("WED_4"),
    WED_5("WED_5"),
    WED_6("WED_6"),
    WED_7("WED_7"),
    WED_8("WED_8"),

    THU_1("THU_1"),
    THU_2("THU_2"),
    THU_3("THU_3"),
    THU_4("THU_4"),
    THU_5("THU_5"),
    THU_6("THU_6"),
    THU_7("THU_7"),
    THU_8("THU_8"),

    FRI_1("FRI_1"),
    FRI_2("FRI_2"),
    FRI_3("FRI_3"),
    FRI_4("FRI_4"),
    FRI_5("FRI_5"),
    FRI_6("FRI_6"),
    FRI_7("FRI_7"),
    FRI_8("FRI_8"),

    SAT_1("SAT_1"),
    SAT_2("SAT_2"),
    SAT_3("SAT_3"),
    SAT_4("SAT_4"),
    SAT_5("SAT_5"),
    SAT_6("SAT_6"),
    SAT_7("SAT_7"),
    SAT_8("SAT_8");

    private final String key;
}
