package com.kwu.swe.domain.assignment.repository;

import com.kwu.swe.domain.assignment.dto.AssignmentResponseDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByLectureId(Long lecturerId);

}
