package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentDto;

public interface AssignmentCommandService {
    // 과제 생성
    Long createAssignment(AssignmentDto assignmentDto);

    // 과제 수정
    void updateAssignment(Long assignmentId, AssignmentDto assignmentDto);

    // 과제 삭제
    void deleteAssignment(Long assignmentId);
}
