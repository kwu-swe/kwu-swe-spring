package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentResponseDto;
import com.kwu.swe.domain.assignment.entity.Assignment;

import java.util.List;

public interface AssignmentQueryService {

    List<Assignment> findAssignmentsByLectureId(Long lecturerId);

    Assignment getAssignmentByLectureIdAndAssignmentId(Long lectureId, Long assignmentId);
}
