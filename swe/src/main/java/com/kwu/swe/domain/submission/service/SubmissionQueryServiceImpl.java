package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.submission.dto.SubmissionProfessorResponseDto;
import com.kwu.swe.domain.submission.dto.SubmitStatus;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import com.kwu.swe.domain.user.entity.LectureStudent;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.LectureStudentRepository;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubmissionQueryServiceImpl implements SubmissionQueryService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;
    private final LectureStudentRepository lectureStudentRepository;

    @Override
    public Optional<Submission> findSubmissionByAssignmentIdAndUserId(Long assignmentId, String code) {

        // code로부터 사용자 조회
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        return submissionRepository.findSubmissionByAssignmentIdAndUserId(assignmentId, user.getId());
    }

    @Override
    public List<SubmissionProfessorResponseDto> findAllStatusOfAssignment(Long assignmentId, String code) {
        User user = userRepository.findUserByCode(code)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        if(!user.getRole().equals(Role.ROLE_PROFESSOR))
            throw new GeneralException(ErrorStatus.ONLY_TOUCHED_BY_PROFESSOR);

        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.ASSIGNMENT_NOT_FOUND));

        List<LectureStudent> byLecture = lectureStudentRepository.findByLecture(assignment.getLecture());
        return byLecture.stream().map(lectureStudent -> {
            boolean result = submissionRepository.existsByUserId(lectureStudent.getStudent().getId());
            return SubmissionProfessorResponseDto.builder()
                    .submitStatus(result ? SubmitStatus.SUBMITTED : SubmitStatus.NOT_SUBMITTED)
                    .studentId(lectureStudent.getStudent().getId())
                    .studentName(lectureStudent.getStudent().getName())
                    .build();
        }).toList();
    }

    @Override
    public Submission findSubmissionByAssignmentIdAndUserId(Long assignmentId, Long studentId) {
        Submission submission = submissionRepository.findSubmissionByAssignmentIdAndUserId(assignmentId, studentId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.ASSIGNMENT_NOT_FOUND));
        return submission;
    }
}
