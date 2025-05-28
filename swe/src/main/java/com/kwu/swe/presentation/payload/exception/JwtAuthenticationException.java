package com.kwu.swe.presentation.payload.exception;

import com.kwu.swe.presentation.payload.code.ErrorStatus;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(ErrorStatus errorStatus) {
        super(errorStatus.name());
    }
}
