package com.kwu.swe.domain.assignment.controller;

import com.kwu.swe.domain.assignment.dto.AssignmentDto;
import com.kwu.swe.domain.assignment.service.AssignmentCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentApiController {

    private final AssignmentCommandService assignmentService;

    // 과제 생성
    @PostMapping
    public ResponseEntity<String> createAssignment(@RequestBody AssignmentDto assignmentDto) {
        Long assignmentId = assignmentService.createAssignment(assignmentDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("과제가 성공적으로 생성되었습니다. 과제 ID: " + assignmentId);
    }
}
