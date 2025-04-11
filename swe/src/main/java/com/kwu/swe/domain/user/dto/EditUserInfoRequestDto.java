package com.kwu.swe.domain.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditUserInfoRequestDto {
    private String password;
    private String phoneNumber;
}
