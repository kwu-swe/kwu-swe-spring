package com.kwu.swe.domain.submission.service;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.entity.AssignmentFile;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import com.kwu.swe.domain.submission.entity.SubmissionStatus;
import com.kwu.swe.domain.submission.repository.SubmissionFileRepository;
import com.kwu.swe.domain.submission.repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmissionCommandServiceImpl implements SubmissionCommandService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubmissionFileRepository submissionFileRepository;

    //TODO C, U 기능은 Long type으로 반환
    @Override
    public Long submitSubmissionAndUpdateStatus(long assignmentId, String title, String content, List<String> encodedFiles) {
        // 제출할 과제 찾기
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과제가 존재하지 않습니다."));

        // 제출일과 과제의 마감일을 비교
        LocalDateTime currentTime = LocalDateTime.now(); // 현재 날짜
        LocalDateTime dueDate = assignment.getDueDate(); // 과제의 마감일

        if(currentTime.isAfter(dueDate)) {
            throw new IllegalArgumentException("마감일이 지난 과제는 제출할 수 없습니다.");
        }

        // 제출할 과제에 대한 Submission 객체 생성
        Submission submission = Submission.builder()
                .assignment(assignment)
                .title(title) // 제목 업데이트
                .content(content) // 내용 업데이트
                .status(SubmissionStatus.SUBMITTED)
                .build();

        submissionRepository.save(submission);


        for (int i = 0; i < encodedFiles.size(); i++) {
            String encodedResult = encodedFiles.get(i);

            SubmissionFile submissionFile = SubmissionFile.builder()
                    .encodedResult(encodedResult)
                    .submission(submission)  // 연결된 Submission 설정
                    .build();

            submissionFileRepository.save(submissionFile);
        }

        return submission.getId();
    }
}
