package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.dto.SubmissionDto;
import org.springframework.web.multipart.MultipartFile;

public interface SubmissionCommandService {
    void createSubmission(SubmissionDto submissionDto);

    // 파일 업로드 메서드 추가
    void uploadSubmissionFile(Long submissionId, MultipartFile file);
}
