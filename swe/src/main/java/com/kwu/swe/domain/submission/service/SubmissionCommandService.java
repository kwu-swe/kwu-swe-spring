package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.dto.SubmissionDto;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import org.springframework.web.multipart.MultipartFile;

public interface SubmissionCommandService {
    void createSubmission(SubmissionDto submissionDto);

    // 파일 업로드 메서드 추가
    void uploadSubmissionFile(Long submissionId, MultipartFile file);

    // 파일 삭제 메서드 추가
    void deleteSubmissionFile(Long submissionId);
}
