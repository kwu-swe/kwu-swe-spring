package com.kwu.swe.domain.user.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade implements KeyedEnum {
    A_PLUS("A_PLUS"),
    A("A"),
    B_PLUS("B_PLUS"),
    B("B"),
    C_PLUS("C_PLUS"),
    C("C"),
    D_PLUS("D_PLUS"),
    D("D"),
    F("F"),
    IN_PROGRESS("IN_PROGRESS");

    private final String key;
}
