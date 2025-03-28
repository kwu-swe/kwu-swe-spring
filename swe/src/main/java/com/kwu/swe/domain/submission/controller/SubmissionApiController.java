package com.kwu.swe.domain.submission.controller;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.service.SubmissionCommandService;
import com.kwu.swe.domain.submission.service.SubmissionQueryService;
import com.kwu.swe.domain.submission.dto.SubmissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionApiController {

    private final SubmissionCommandService submissionCommandService;
    private final SubmissionQueryService submissionQueryService;

    // 과제 제출 생성
    @PostMapping
    public ResponseEntity<String> submitAssignment(@RequestBody SubmissionDto submissionDto) {
        submissionCommandService.createSubmission(submissionDto);
        return ResponseEntity.status(201).body("제출이 완료되었습니다.");
    }

    // ✅ 과제 ID 기준으로 제출 조회
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByAssignmentId(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(submissionQueryService.getSubmissionsByAssignmentId(assignmentId));
    }

    // ✅ 학생 ID 기준으로 제출 조회
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Submission>> getSubmissionsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(submissionQueryService.getSubmissionsByStudentId(studentId));
    }

    // ✅ 과제 + 학생 ID 기준 단건 조회
    @GetMapping("/assignment/{assignmentId}/student/{studentId}")
    public ResponseEntity<Submission> getSubmissionByAssignmentAndStudent(
            @PathVariable Long assignmentId,
            @PathVariable Long studentId) {

        return submissionQueryService
                .getSubmissionByAssignmentIdAndStudentId(assignmentId, studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
