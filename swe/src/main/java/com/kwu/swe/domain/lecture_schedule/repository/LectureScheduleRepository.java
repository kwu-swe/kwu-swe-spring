package com.kwu.swe.domain.lecture_schedule.repository;

import com.kwu.swe.domain.lecture_schedule.entity.LectureSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureScheduleRepository extends JpaRepository<LectureSchedule, Long> {
}
