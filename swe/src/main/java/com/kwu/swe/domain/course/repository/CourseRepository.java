package com.kwu.swe.domain.course.repository;

import com.kwu.swe.domain.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
