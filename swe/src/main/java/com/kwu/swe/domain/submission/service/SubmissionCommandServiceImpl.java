package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.dto.AssignmentRequestDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.entity.AssignmentFile;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentRequestDto;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import com.kwu.swe.domain.submission.entity.SubmissionStatus;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.domain.user.repository.UserRepository;
import com.kwu.swe.presentation.payload.code.ErrorStatus;
import com.kwu.swe.presentation.payload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.PathMatcher;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmissionCommandServiceImpl implements SubmissionCommandService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubmissionFileRepository submissionFileRepository;
    private final PathMatcher pathMatcher;
    private final UserRepository userRepository;

    //TODO C, U 기능은 Long type으로 반환
    @Override
    public Long submitSubmissionAndUpdateStatus(Long assignmentId, Long userId ,SubmitAssignmentRequestDto submitAssignmentRequestDto) {

        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.ASSIGNMENT_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        // 제출일과 과제의 마감일을 비교
        LocalDateTime currentTime = LocalDateTime.now(); // 현재 날짜
        LocalDateTime dueDate = assignment.getDueDate(); // 과제의 마감일

        if(currentTime.isAfter(dueDate)) {
            throw new IllegalArgumentException("마감일이 지난 과제는 제출할 수 없습니다.");
        }

        // 제출할 과제에 대한 Submission 객체 생성
        Submission submission = Submission.builder()
                .assignment(assignment)
                .user(user)
                .title(submitAssignmentRequestDto.getTitle()) // 제목 업데이트
                .content(submitAssignmentRequestDto.getContent()) // 내용 업데이트
                .status(SubmissionStatus.SUBMITTED)
                .build();

        submissionRepository.save(submission);

        for (int i = 0; i < submitAssignmentRequestDto.getEncodedFiles().size(); i++) {
            String encodedResult = submitAssignmentRequestDto.getEncodedFiles().get(i);

            SubmissionFile submissionFile = SubmissionFile.builder()
                    .encodedURL(encodedResult)
                    .submission(submission)  // 연결된 Submission 설정
                    .build();

            submissionFileRepository.save(submissionFile);
        }

        return submission.getId();
    }

    public Long updateSubmission(Long submissionId, SubmitAssignmentRequestDto submitAssignmentRequestDto) {

        // submissionId로 과제 조회
        Submission submission = submissionRepository.findById(submissionId)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.SUBMISSION_NOT_FOUND));

        submission.update(submitAssignmentRequestDto.getTitle(), submitAssignmentRequestDto.getContent());

        submissionFileRepository.deleteBySubmission(submission);

        for (int i = 0; i < submitAssignmentRequestDto.getEncodedFiles().size(); i++) {
            String encodedResult = submitAssignmentRequestDto.getEncodedFiles().get(i);

            // SubmissionFile 엔티티 생성
            SubmissionFile submissionFile = SubmissionFile.builder()
                    .encodedURL(encodedResult)
                    .submission(submission)  // 연결된 Submission 설정
                    .build();

            // SubmissionFile 엔티티를 저장
            submissionFileRepository.save(submissionFile);
        }

        return submission.getId();
    }

    @Override
    public void deleteSubmission(Long submissionId) {
        // submissionId로 제출한 과제 조회
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.SUBMISSION_NOT_FOUND));

        // 제출한 과제 삭제
        submissionRepository.delete(submission);
    }
}
