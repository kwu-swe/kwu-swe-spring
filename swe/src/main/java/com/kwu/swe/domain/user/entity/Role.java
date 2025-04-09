package com.kwu.swe.domain.user.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role implements KeyedEnum {
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_PROFESSOR("ROLE_PROFESSOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String key;
}
