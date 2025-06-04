package com.kwu.swe.domain.material.repository;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.material.entity.Material;
import com.kwu.swe.domain.material.entity.MaterialFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialFileRepository extends JpaRepository<MaterialFile, Long> {
    void deleteByMaterial(Material material);
}
