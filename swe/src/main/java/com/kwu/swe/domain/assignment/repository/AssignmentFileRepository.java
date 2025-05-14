package com.kwu.swe.domain.assignment.repository;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.entity.AssignmentFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentFileRepository extends JpaRepository<AssignmentFile, Long> {
    void deleteByAssignment(Assignment assignment);
}
