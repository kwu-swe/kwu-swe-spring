package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.submission.dto.SubmissionProfessorResponseDto;
import com.kwu.swe.domain.submission.entity.Submission;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface SubmissionQueryService {

    Optional<Submission> findSubmissionByAssignmentIdAndUserId(Long assignmentId, String code);

    List<SubmissionProfessorResponseDto> findAllStatusOfAssignment(Long assignment, String code);

    Submission findSubmissionByAssignmentIdAndUserId(Long assignmentId, Long studentId);

}
