package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.Submission;

import java.util.List;
import java.util.Optional;

public interface SubmissionQueryService {

    List<Submission> getSubmissionsByAssignmentId(Long assignmentId);

    List<Submission> getSubmissionsByStudentId(Long studentId);

    Optional<Submission> getSubmissionByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
}
