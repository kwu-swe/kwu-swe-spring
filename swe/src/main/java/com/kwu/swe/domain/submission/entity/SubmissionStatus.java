package com.kwu.swe.domain.submission.entity;

import com.kwu.swe.global.interfaces.KeyedEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmissionStatus implements KeyedEnum {
    SUBMITTED("제출 완료"), // 제출 완료
    LATE("제출 지각"); // 지각 제출

    private final String key;
}
