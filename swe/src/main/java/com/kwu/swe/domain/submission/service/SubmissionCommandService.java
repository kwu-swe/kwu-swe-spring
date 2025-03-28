package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.dto.SubmissionDto;

public interface SubmissionCommandService {
    void createSubmission(SubmissionDto submissionDto);
}

