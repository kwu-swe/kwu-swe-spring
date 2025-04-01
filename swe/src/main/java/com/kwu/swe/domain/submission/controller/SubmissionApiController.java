package com.kwu.swe.domain.submission.controller;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.service.SubmissionCommandService;
import com.kwu.swe.domain.submission.service.SubmissionFileService; // 추가
import com.kwu.swe.domain.submission.service.SubmissionQueryService;
import com.kwu.swe.domain.submission.dto.SubmissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionApiController {

    private final SubmissionCommandService submissionCommandService;
    private final SubmissionQueryService submissionQueryService;
    private final SubmissionFileService submissionFileService;  // 추가

    // 과제 제출 생성
    @PostMapping
    public ResponseEntity<String> submitAssignment(@RequestBody SubmissionDto submissionDto) {
        submissionCommandService.createSubmission(submissionDto);
        return ResponseEntity.status(201).body("제출이 완료되었습니다.");
    }

    // ✅ 과제 ID 기준으로 제출 조회
    @GetMapping("/assignments/{assignmentId}")
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

    // ✅ 파일 업로드
    @PostMapping("/{submissionId}/file")
    public ResponseEntity<String> uploadFile(@PathVariable Long submissionId, @RequestParam("file") MultipartFile file) {
        submissionCommandService.uploadSubmissionFile(submissionId, file);
        return ResponseEntity.status(201).body("파일이 업로드되었습니다.");
    }

    // ✅ 파일 다운로드
    @GetMapping("/{submissionId}/file")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long submissionId) {
        byte[] fileData = submissionFileService.downloadFile(submissionId);  // Service에서 파일 다운로드 처리
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "submissionFile") // 파일 이름 추가
                .body(fileData);  // 바이트 데이터 반환
    }

    // 파일 삭제
    @DeleteMapping("/{submissionId}/file")
    public ResponseEntity<String> deleteFile(@PathVariable Long submissionId) {
        submissionCommandService.deleteSubmissionFile(submissionId);
        return ResponseEntity.ok("파일이 삭제되었습니다.");
    }

    // 제출 요청 엔드포인트 추가
    @PostMapping("/{submissionId}/submit")
    public ResponseEntity<String> submitAssignment(@PathVariable Long submissionId) {
        submissionCommandService.submitAssignment(submissionId);
        return ResponseEntity.status(200).body("제출이 완료되었습니다.");
    }
}
