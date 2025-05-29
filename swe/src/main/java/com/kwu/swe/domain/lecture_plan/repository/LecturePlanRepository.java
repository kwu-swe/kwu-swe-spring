package com.kwu.swe.domain.lecture_plan.repository;

import com.kwu.swe.domain.announcement.entity.Announcement;
import com.kwu.swe.domain.lecture_plan.entity.LecturePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturePlanRepository extends JpaRepository<LecturePlan, Long> {
    LecturePlan findByLectureId(Long lectureId);
}
