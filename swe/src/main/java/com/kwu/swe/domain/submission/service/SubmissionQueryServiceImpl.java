package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmissionQueryServiceImpl implements SubmissionQueryService {

    private final SubmissionRepository submissionRepository;

    @Override
    public Submission findSubmissionByAssignmentIdAndUserId(Long assignmentId, Long userId) {
        return submissionRepository.findSubmissionByAssignmentIdAndUserId(assignmentId, userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.SUBMISSION_NOT_FOUND));
    }
}
