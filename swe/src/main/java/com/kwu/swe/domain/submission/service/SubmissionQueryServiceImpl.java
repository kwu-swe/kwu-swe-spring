package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public Submission findSubmissionByAssignmentIdAndUserId(Long assignmentId, String code) {

        // code로부터 사용자 조회
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        return submissionRepository.findSubmissionByAssignmentIdAndUserId(assignmentId, user.getId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.SUBMISSION_NOT_FOUND));
    }
}
