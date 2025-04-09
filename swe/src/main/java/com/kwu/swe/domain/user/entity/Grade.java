package com.kwu.swe.domain.user.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade implements KeyedEnum {
    A_PLUS("A PLUS"),
    A("A"),
    B_PLUS("B"),
    B("B"),
    C_PLUS("C PLUS"),
    C("C"),
    D_PLUS("D PLUS"),
    D("D"),
    F("F"),
    IN_PROGRESS("IN PROGRESS");

    private final String key;
}
