package com.kwu.swe.domain.assignment.controller;

import com.kwu.swe.domain.assignment.dto.AssignmentDto;
import com.kwu.swe.domain.assignment.service.AssignmentCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentApiController {

    private final AssignmentCommandService assignmentCommandService;

    // 과제 생성
    @PostMapping
    public ResponseEntity<String> createAssignment(@RequestBody AssignmentDto assignmentDto) {
        Long assignmentId = assignmentCommandService.createAssignment(assignmentDto);
        return ResponseEntity.status(201).body("과제 생성 완료. ID: " + assignmentId);
    }

    // 과제 수정
    @PutMapping("/{assignmentId}")
    public ResponseEntity<String> updateAssignment(@PathVariable Long assignmentId, @RequestBody AssignmentDto assignmentDto) {
        assignmentCommandService.updateAssignment(assignmentId, assignmentDto);
        return ResponseEntity.ok("과제 수정 완료");
    }

    // 과제 삭제
    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<String> deleteAssignment(@PathVariable Long assignmentId) {
        assignmentCommandService.deleteAssignment(assignmentId);
        return ResponseEntity.ok("과제 삭제 완료");
    }
}
