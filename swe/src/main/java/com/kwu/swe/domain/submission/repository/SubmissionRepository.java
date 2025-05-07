package com.kwu.swe.domain.submission.repository;

import com.kwu.swe.domain.submission.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
