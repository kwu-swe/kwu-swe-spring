package com.kwu.swe.domain.user.dto;

import com.kwu.swe.domain.user.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private String name;
    private String studentNumber;
    private String phoneNumber;
    private Role role;
    //TODO MAJOR
}
