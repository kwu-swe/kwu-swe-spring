package com.kwu.swe.domain.lecture_location.repository;

import com.kwu.swe.domain.lecture_location.entity.LectureLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureLocationRepository extends JpaRepository<LectureLocation, Long> {
}
