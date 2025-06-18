package com.kwu.swe.domain.submission.dto;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmitStatus implements KeyedEnum {
    SUBMITTED("SUBMITTED"), NOT_SUBMITTED("SUBMITTED");

    private final String key;
}
