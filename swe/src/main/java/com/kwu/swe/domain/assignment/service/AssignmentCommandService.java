package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentDto;

public interface AssignmentCommandService {
    Long createAssignment(AssignmentDto assignmentDto);  // 과제 생성 메서드
}
