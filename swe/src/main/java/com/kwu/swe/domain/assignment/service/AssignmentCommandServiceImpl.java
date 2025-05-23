package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentRequestDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.entity.AssignmentFile;
import com.kwu.swe.domain.assignment.repository.AssignmentFileRepository;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.repository.LectureRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignmentCommandServiceImpl implements AssignmentCommandService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentFileRepository assignmentFileRepository;
    private final LectureRepository lectureRepository;

    @Override
    public Long createAssignment(AssignmentRequestDto assignmentRequestDto, Long lectureId) {

        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(RuntimeException::new);

        // 현재 날짜를 기준으로 마감일 계산
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime dueDate = currentDate.plusDays(assignmentRequestDto.getDueDateAfterDays());

        // AssignmentDto로부터 Assignment 엔티티를 생성
        Assignment assignment = Assignment.builder()
                .title(assignmentRequestDto.getTitle())
                .content(assignmentRequestDto.getContent())
                .dueDate(dueDate)
                .lecture(lecture)
                .build();

        // Assignment 엔티티를 저장
        Assignment savedAssignment = assignmentRepository.save(assignment);

        // AssignmentDto로부터 AssignmentFile 엔티티를 생성 및 저장
        List<String> encodedFiles = assignmentRequestDto.getEncodedFiles();

        for (int i = 0; i < encodedFiles.size(); i++) {
            String encodedResult = encodedFiles.get(i);

            // AssignmentFile 엔티티 생성
            AssignmentFile assignmentFile = AssignmentFile.builder()
                    .encodedURL(encodedResult)
                    .assignment(savedAssignment)  // 연결된 Assignment 설정
                    .build();

            // AssignmentFile 엔티티를 저장
            assignmentFileRepository.save(assignmentFile);
        }

        // 저장된 Assignment의 ID를 반환
        return savedAssignment.getId();
    }

    public Long updateAssignment(Long assignmentId, AssignmentRequestDto assignmentRequestDto) {

        // assignmentId로 과제 조회
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(RuntimeException::new);

        assignment.update(assignmentRequestDto.getTitle(), assignmentRequestDto.getContent());

        assignmentFileRepository.deleteByAssignment(assignment);

        for (int i = 0; i < assignmentRequestDto.getEncodedFiles().size(); i++) {
            String encodedResult = assignmentRequestDto.getEncodedFiles().get(i);

            // AssignmentFile 엔티티 생성
            AssignmentFile assignmentFile = AssignmentFile.builder()
                    .encodedURL(encodedResult)
                    .assignment(assignment)  // 연결된 Assignment 설정
                    .build();

            // AssignmentFile 엔티티를 저장
            assignmentFileRepository.save(assignmentFile);
        }

        return assignment.getId();
    }

    public void deleteAssignment(Long assignmentId) {

        // assignmentId로 과제 조회
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(RuntimeException::new);

        // 과제 삭제
        assignmentRepository.delete(assignment);
    }
}
