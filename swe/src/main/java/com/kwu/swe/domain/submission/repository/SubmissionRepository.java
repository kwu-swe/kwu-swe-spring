package com.kwu.swe.domain.submission.repository;

import com.kwu.swe.domain.submission.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    // 특정 과제 ID로 모든 제출 조회
    List<Submission> findByAssignmentId(Long assignmentId);

    // 특정 학생이 제출한 모든 과제
    List<Submission> findByStudentId(Long studentId);

    // 특정 과제에 대해 특정 학생의 제출 조회
    Optional<Submission> findByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
}
