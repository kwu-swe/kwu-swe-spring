package com.kwu.swe.domain.lecture.repository;

import com.kwu.swe.domain.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
