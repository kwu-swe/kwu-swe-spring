package com.kwu.swe.domain.user.service;

import com.kwu.swe.domain.user.dto.UserResponseDto;
import com.kwu.swe.domain.user.entity.User;

public interface UserQueryService {
    User getUserInfo(String code);
}
