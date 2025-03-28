package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.submission.dto.SubmissionDto;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionStatus;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubmissionCommandServiceImpl implements SubmissionCommandService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;  // Assignment 레포지토리 추가

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
}
