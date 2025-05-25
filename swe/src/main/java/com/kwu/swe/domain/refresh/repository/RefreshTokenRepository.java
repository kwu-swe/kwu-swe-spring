package com.kwu.swe.domain.refresh.repository;

import com.kwu.swe.domain.refresh.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByValue(String value);

    boolean existsByValue(String value);
}
