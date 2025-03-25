package com.kwu.swe.domain.lecture.repository;

import com.kwu.swe.domain.lecture.entity.LectureLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureLocationRepository extends JpaRepository<LectureLocation, Long> {
}
