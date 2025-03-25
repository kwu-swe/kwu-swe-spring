package com.kwu.swe.domain.lecture.repository;

import com.kwu.swe.domain.lecture.entity.LectureSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureScheduleRepository extends JpaRepository<LectureSchedule, Long> {
}
