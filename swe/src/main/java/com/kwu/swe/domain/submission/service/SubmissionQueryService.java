package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.Submission;

public interface SubmissionQueryService {
    Submission getSubmissionByUserAndAssignment(String studentNumber, Long assignmentId);
}
