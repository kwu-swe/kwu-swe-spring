package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SubmissionQueryService {
    Submission getSubmissionByUserAndAssignment(String userId, Long assignmentId);

    List<SubmissionFile> getSubmissionByUserAndAssignment(Long submissionId);

/*    List<MultipartFile> decodeFileToMultipartFile(Long submissionId) throws IOException;*/
}
