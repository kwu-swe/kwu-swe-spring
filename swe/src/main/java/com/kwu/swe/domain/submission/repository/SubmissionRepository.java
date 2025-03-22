package com.kwu.swe.domain.submission.repository;

import com.kwu.swe.domain.submission.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
