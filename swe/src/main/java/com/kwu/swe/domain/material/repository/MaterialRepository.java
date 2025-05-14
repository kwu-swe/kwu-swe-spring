package com.kwu.swe.domain.material.repository;

import com.kwu.swe.domain.material.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
