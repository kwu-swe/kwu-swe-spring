package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.submission.entity.Submission;

import java.util.List;

public interface SubmissionCommandService {

    Long submitSubmissionAndUpdateStatus(long assignmentId, String title, String content, List<String> encodedFiles);
}
