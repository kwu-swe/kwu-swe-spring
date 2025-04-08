package com.kwu.swe.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserRequestDto {
    private String name;
    private String password;
    private String studentNumber;
    private String phoneNumber;
}
