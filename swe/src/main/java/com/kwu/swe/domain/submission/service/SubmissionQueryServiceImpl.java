package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubmissionQueryServiceImpl implements SubmissionQueryService {

    private final SubmissionRepository submissionRepository;

    @Override
    public List<Submission> getSubmissionsByAssignmentId(Long assignmentId) {
        return submissionRepository.findByAssignmentId(assignmentId);
    }

    @Override
    public List<Submission> getSubmissionsByStudentId(Long studentId) {
        return submissionRepository.findByStudentId(studentId);
    }

    @Override
    public Optional<Submission> getSubmissionByAssignmentIdAndStudentId(Long assignmentId, Long studentId) {
        return submissionRepository.findByAssignmentIdAndStudentId(assignmentId, studentId);
    }
}
