package com.kwu.swe.domain.lecture_plan.repository;

import com.kwu.swe.domain.announcement.entity.Announcement;
import com.kwu.swe.domain.lecture_plan.entity.LecturePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecturePlanRepository extends JpaRepository<LecturePlan, Long> {
    Optional<LecturePlan> findByLectureId(Long lectureId);
}
