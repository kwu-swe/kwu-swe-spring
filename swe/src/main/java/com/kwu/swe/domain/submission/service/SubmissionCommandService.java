package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.dto.SubmitAssignmentRequestDto;

public interface SubmissionCommandService {

    Long submitSubmissionAndUpdateStatus(Long assignmentId, String code, SubmitAssignmentRequestDto submitAssignmentRequestDto);

    Long updateSubmission(Long submissionId, SubmitAssignmentRequestDto submitAssignmentRequestDto);

    void deleteSubmission(Long submissionId);
}
