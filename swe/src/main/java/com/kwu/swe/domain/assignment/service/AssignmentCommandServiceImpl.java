package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AssignmentCommandServiceImpl implements AssignmentCommandService {

    private final AssignmentRepository assignmentRepository;

    @Override
    public Long createAssignment(AssignmentDto assignmentDto) {

        // 현재 날짜를 기준으로 마감일 계산
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime dueDate = currentDate.plusDays(assignmentDto.getDueDateAfterDays());

        // AssignmentDto로부터 Assignment 엔티티를 생성
        Assignment assignment = Assignment.builder()
                .title(assignmentDto.getTitle())
                .content(assignmentDto.getContent())
                .dueDate(dueDate)
                .build();

        // Assignment 엔티티를 저장
        Assignment savedAssignment = assignmentRepository.save(assignment);

        // 저장된 Assignment의 ID를 반환
        return savedAssignment.getId();
    }
}
