package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentRequestDto;
import com.kwu.swe.domain.submission.entity.Submission;

import java.util.List;

public interface SubmissionCommandService {

    Long submitSubmissionAndUpdateStatus(Long userId, Long assignmentId, SubmitAssignmentRequestDto submitAssignmentRequestDto);

    Long updateSubmission(Long submissionId, SubmitAssignmentRequestDto submitAssignmentRequestDto);

    void deleteSubmission(Long submissionId);
}
