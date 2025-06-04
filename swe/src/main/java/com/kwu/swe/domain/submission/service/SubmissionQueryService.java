package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.submission.entity.Submission;

import java.util.Optional;

public interface SubmissionQueryService {

    Optional<Submission> findSubmissionByAssignmentIdAndUserId(Long assignmentId, String code);
}
