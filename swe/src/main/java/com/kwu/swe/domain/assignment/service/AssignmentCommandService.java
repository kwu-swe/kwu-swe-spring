package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentRequestDto;

public interface AssignmentCommandService {
    Long createAssignment(AssignmentRequestDto assignmentRequestDto, Long lectureId);  // 과제 생성 메서드

    // 특정 assignmentId에 대한 Assignment 수정
    Long updateAssignment(Long assignmentId, AssignmentRequestDto assignmentRequestDto);

    void deleteAssignment(Long assignmentId);
}
