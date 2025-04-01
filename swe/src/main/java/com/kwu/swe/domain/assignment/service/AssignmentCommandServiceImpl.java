package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentCommandServiceImpl implements AssignmentCommandService {

    private final AssignmentRepository assignmentRepository;  // 과제 Repository

    // 과제 생성
    @Override
    public Long createAssignment(AssignmentDto assignmentDto) {
        Assignment assignment = Assignment.builder()
//                .lectureId(assignmentDto.getLectureId())
                .title(assignmentDto.getTitle())
                .content(assignmentDto.getContent())
                .dueDate(assignmentDto.getDueDate())
                .extendedDueDate(assignmentDto.getExtendedDueDate())
                .allowSubmission(assignmentDto.isAllowSubmission())
                .isPublic(assignmentDto.isPublic())
                .build();

        Assignment savedAssignment = assignmentRepository.save(assignment);
        return savedAssignment.getId();  // 생성된 과제 ID 반환
    }

    // 과제 수정
    @Override
    public void updateAssignment(Long assignmentId, AssignmentDto assignmentDto) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과제가 존재하지 않습니다."));

        assignment.updateAssignment(assignmentDto);  // Entity에서 update 메서드 호출

        assignmentRepository.save(assignment);  // 수정된 과제 저장
    }

    // 과제 삭제
    @Override
    public void deleteAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과제가 존재하지 않습니다."));

        assignmentRepository.delete(assignment);  // 과제 삭제
    }
}
