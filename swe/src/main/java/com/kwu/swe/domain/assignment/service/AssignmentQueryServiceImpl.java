package com.kwu.swe.domain.assignment.service;

import com.kwu.swe.domain.assignment.dto.AssignmentResponseDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentQueryServiceImpl implements AssignmentQueryService {

    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Assignment> findAssignmentsByLectureId(Long lecturerId) {
        return assignmentRepository.findByLectureId(lecturerId);
    }

    @Override
     public Assignment getAssignmentByLectureIdAndAssignmentId(Long lectureId, Long assignmentId) {
        return assignmentRepository.findByLectureIdAndId(lectureId, assignmentId);
    }

}
