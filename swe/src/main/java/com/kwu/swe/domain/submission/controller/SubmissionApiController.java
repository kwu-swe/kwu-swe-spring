package com.kwu.swe.domain.submission.controller;

import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.service.FileEncodingService;
import com.kwu.swe.domain.submission.service.SubmissionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionApiController {

    private final FileEncodingService fileEncodingService;
    private final SubmissionCommandService submissionCommandService;

    @PostMapping(value = "/encode", consumes = "multipart/form-data") // consumes를 추가
    public ResponseEntity<String> encodeFile(@RequestPart("file") MultipartFile file) {
        try {
            // 파일 인코딩 처리
            String encodedResult = fileEncodingService.encodeFile(file);
            return ResponseEntity.ok(encodedResult);  // 인코딩된 파일을 반환
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("파일 인코딩 처리 중 오류가 발생했습니다.");
        }
    }

    @PostMapping(value = "/{submissionId}/submit", consumes = "multipart/form-data")
    public ResponseEntity<String> submitAssignment(
            @PathVariable Long submissionId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        try {
            // 파일을 인코딩하여 결과를 받음
            String encodedResult = fileEncodingService.encodeFile(file);

            // 제출된 파일, 제목, 내용 및 인코딩 결과로 과제 상태 업데이트
            submissionCommandService.submitFileAndUpdateStatus(submissionId, file, title, content, encodedResult);

            // 성공 메시지와 함께 반환
            return ResponseEntity.status(HttpStatus.OK)
                    .body("과제가 성공적으로 제출되었습니다.");
        } catch (IOException e) {
            // 오류 발생 시 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("파일 인코딩 처리 중 오류가 발생했습니다.");
        }
    }

    // 특정 submissionId로 제출된 과제 조회
    @GetMapping("/submissions/{submissionId}")
    public ResponseEntity<Submission> getSubmission(@PathVariable Long submissionId) {
        Submission submission = submissionCommandService.getSubmissionById(submissionId);
        return ResponseEntity.ok(submission);  // 제출된 과제 정보 반환
    }
}

