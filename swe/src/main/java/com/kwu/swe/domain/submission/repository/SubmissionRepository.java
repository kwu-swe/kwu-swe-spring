package com.kwu.swe.domain.submission.repository;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> user(User user);

    Optional<Submission> findSubmissionByAssignmentIdAndUserId(Long assignmentId, Long userId);

    boolean existsByUserId(Long id);

    boolean existsByUserIdAndAssignmentId(Long id, Long assignmentId);
}
