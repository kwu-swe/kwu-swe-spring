package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.dto.SubmissionDto;
import com.kwu.swe.domain.submission.entity.Submission;
import org.springframework.web.multipart.MultipartFile;

public interface SubmissionCommandService {
    Long createSubmission(SubmissionDto submissionDto);  // 제출 생성 메서드

    void uploadSubmissionFile(Long submissionId, MultipartFile file);  // 파일 업로드 메서드

    void deleteSubmissionFile(Long submissionId);  // 파일 삭제 메서드

    // submissionId로 제출된 과제를 조회
    Submission getSubmissionById(Long submissionId);

    void submitAssignment(Long submissionId);  // 제출 상태 변경 메서드

    // 인코딩 결과 및 파일 제출 후 상태 변경 메서드
    void submitFileAndUpdateStatus(Long submissionId, MultipartFile file, String title, String content, String encodedResult);
}
