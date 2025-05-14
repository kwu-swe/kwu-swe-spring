package com.kwu.swe.domain.user.dto;

import com.kwu.swe.domain.user.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDto {

    private String name;
    private String code;
    private String phoneNumber;
    private Role role;
    private LocalDateTime createdAt;
    //TODO MAJOR
}
