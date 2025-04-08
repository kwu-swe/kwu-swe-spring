package com.kwu.swe.domain.user.repository;

import com.kwu.swe.domain.user.entity.LectureStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureStudentRepository extends JpaRepository<LectureStudent, Long> {
}
