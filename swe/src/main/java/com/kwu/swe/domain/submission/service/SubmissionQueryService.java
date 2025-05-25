package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.submission.entity.Submission;

public interface SubmissionQueryService {

    Submission findSubmissionByAssignmentIdAndUserId(Long assignmentId, Long userId);
}
