package com.kwu.swe.domain.lecture_plan.repository;

import com.kwu.swe.domain.lecture_plan.entity.LecturePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturePlanRepository extends JpaRepository<LecturePlan, Long> {
}
