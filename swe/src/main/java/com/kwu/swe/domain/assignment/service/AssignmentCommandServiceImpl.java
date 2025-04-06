package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentCommandServiceImpl implements AssignmentCommandService {

    private final AssignmentRepository assignmentRepository;

    @Override
    public Long createAssignment(AssignmentDto assignmentDto) {
        // AssignmentDto로부터 Assignment 엔티티를 생성
        Assignment assignment = Assignment.builder()
                .title(assignmentDto.getTitle())
                .content(assignmentDto.getContent())
                .dueDate(assignmentDto.getDueDate())
                .extendedDueDate(assignmentDto.getExtendedDueDate())
                .build();

        // Assignment 엔티티를 저장
        Assignment savedAssignment = assignmentRepository.save(assignment);

        // 저장된 Assignment의 ID를 반환
        return savedAssignment.getId();
    }
}
