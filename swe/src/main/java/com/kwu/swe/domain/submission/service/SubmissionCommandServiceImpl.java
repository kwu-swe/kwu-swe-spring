package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.submission.dto.SubmissionDto;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionStatus;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionCommandServiceImpl implements SubmissionCommandService {

    @Autowired
    private final SubmissionRepository submissionRepository;
    private final FileEncodingService fileEncodingService; // 인코딩 서비스 추가

    @Override
    public Long createSubmission(SubmissionDto submissionDto) {
        // assignmentId로 Assignment 객체 조회
        Assignment assignment = assignmentRepository.findById(submissionDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과제가 존재하지 않습니다."));

        // Submission 객체 생성 (빌더 패턴 활용)
        Submission submission = Submission.builder()
                .assignment(assignment)
                .title(submissionDto.getTitle())
                .content(submissionDto.getContent())
                .encodingResult(submissionDto.getEncodingResult())
                .status(SubmissionStatus.valueOf(submissionDto.getStatus()))  // 문자열을 SubmissionStatus로 변환
                .build();

        submissionRepository.save(submission);

        return submission.getId();
    }

    @Override
    public void uploadSubmissionFile(Long submissionId, MultipartFile file) {

    }

    @Override
    public void deleteSubmissionFile(Long submissionId) {

    }

    @Override
    public void submitAssignment(Long submissionId) {

    }

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public void submitFileAndUpdateStatus(Long submissionId, MultipartFile file, String title, String content, String encodedResult) {
        try {
            // 파일 인코딩 처리
            encodedResult = fileEncodingService.encodeFile(file); // 인코딩을 외부 서비스에서 받아온다고 가정

            // 제출할 과제 찾기
            Assignment assignment = assignmentRepository.findById(submissionId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과제가 존재하지 않습니다."));

            // 빌더 패턴을 사용하여 기존 객체를 수정
            Submission submission = Submission.builder()
                    .status(SubmissionStatus.SUBMITTED) // 상태 업데이트
                    .title(title) // 제목 업데이트
                    .content(content) // 내용 업데이트
                    .encodingResult(encodedResult) // 인코딩 결과 업데이트
                    .assignment(assignment) // 과제 정보 설정
                    .build();

            // 제출 상태 저장
            submissionRepository.save(submission);

        } catch (IOException e) {
            throw new RuntimeException("파일 인코딩 처리 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public Submission getSubmissionById(Long submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 제출이 존재하지 않습니다."));
    }
}
