package com.kwu.swe.domain.user.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role implements KeyedEnum {
    ROLE_STUDENT("학생"),
    ROLE_PROFESSOR("교수"),
    ROLE_ADMIN("개발자");

    private final String key;
}
