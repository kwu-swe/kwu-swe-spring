package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.submission.dto.SubmissionDto;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import com.kwu.swe.domain.submission.entity.SubmissionStatus;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionCommandServiceImpl implements SubmissionCommandService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubmissionFileRepository submissionFileRepository;  // SubmissionFileRepository 추가

    @Override
    public void createSubmission(SubmissionDto submissionDto) {
        // assignmentId로 Assignment 객체 조회
        Assignment assignment = assignmentRepository.findById(submissionDto.getAssignmentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과제가 존재하지 않습니다."));

        Submission submission = Submission.builder()
                .assignment(assignment)
                .studentId(submissionDto.getStudentId())
                .status(SubmissionStatus.SUBMITTED)
                .submittedAt(LocalDateTime.now())
                .build();

        submissionRepository.save(submission);
    }

    @Override
    public void uploadSubmissionFile(Long submissionId, MultipartFile file) {
        // 제출이 존재하는지 확인
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 제출이 존재하지 않습니다."));

        // 파일을 SubmissionFile 객체로 변환
        try {
            SubmissionFile submissionFile = SubmissionFile.builder()
                    .submission(submission)
                    .fileName(file.getOriginalFilename())
                    .fileData(file.getBytes())
                    .build();

            // 파일 저장
            submissionFileRepository.save(submissionFile);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void deleteSubmissionFile(Long submissionId) {
        // 제출이 존재하는지 확인
        SubmissionFile submissionFile = submissionFileRepository.findBySubmissionId(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 제출의 파일이 존재하지 않습니다."));

        // 파일 삭제
        submissionFileRepository.delete(submissionFile);
    }
}
