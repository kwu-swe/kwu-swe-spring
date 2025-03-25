package com.kwu.swe.domain.lecture.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Location {
    SAE_BIT("새빛관"),
    BI_MA("비마관"),
    CHAM_BIT("참빛관"),
    HAN_UL("한울관"),
    OFFLINE("오프라인");
    private final String key;
}
