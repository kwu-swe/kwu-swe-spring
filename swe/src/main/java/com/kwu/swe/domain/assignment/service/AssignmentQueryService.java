package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentResponseDto;
import com.kwu.swe.domain.assignment.entity.Assignment;

import java.util.List;

public interface AssignmentQueryService {

    Assignment findByAssignmentId(Long assignmentId);

    List<Assignment> findAssignmentsByLectureId(Long lecturerId);
}
