package com.kwu.swe.domain.user.dto;


import com.kwu.swe.domain.user.entity.Grade;
import com.kwu.swe.domain.user.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GradeResponseDto {

    private Long studentId;
    private String name;
    private String code;
    private Grade grade;
}
