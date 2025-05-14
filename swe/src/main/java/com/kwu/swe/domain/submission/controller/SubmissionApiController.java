package com.kwu.swe.domain.submission.controller;

import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentRequestDto;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentResponseDto;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.entity.SubmissionFile;
import com.kwu.swe.domain.submission.service.SubmissionCommandService;
import com.kwu.swe.domain.submission.service.SubmissionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

/*@PostMapping(value = "/encode", consumes = "multipart/form-data")
    public ResponseEntity<SubmissionFileResponseDto> encodeFile(@RequestPart("file") MultipartFile file) {
        try {
            // 파일 제목
            String originalFilename = file.getOriginalFilename();
            // 파일 인코딩 처리
            String encodedResult = fileEncodingService.encodeFile(file);

            SubmissionFileResponseDto submissionFileResponseDto = SubmissionFileResponseDto.builder()
                    .encodedResult(encodedResult)
                    .fileName(originalFilename)
                    .build();

            return ResponseEntity.ok(submissionFileResponseDto);  // 인코딩된 파일을 반환
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new SubmissionFileResponseDto());
        }
    }*/



    @PostMapping(value = "/lecture/{lectureId}/assignment/{assignmentId}")
    public ResponseEntity<SubmitAssignmentResponseDto> submitAssignment(
            @RequestBody SubmitAssignmentRequestDto submitAssignmentRequestDto){

        // 1. 과제 제출과 상태 업데이트
        Long submissionId = submissionCommandService.submitSubmissionAndUpdateStatus(
                submitAssignmentRequestDto.getAssignmentId(),
                submitAssignmentRequestDto.getTitle(),
                submitAssignmentRequestDto.getContent(),
                submitAssignmentRequestDto.getEncodedFiles());

        // 성공 응답 반환
        SubmitAssignmentResponseDto responseDto = SubmitAssignmentResponseDto.builder()
                .message("과제가 성공적으로 제출되었습니다.")
                .success(true)
                .submissionId(submissionId)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDto);

    }

@GetMapping("/lecture/{lectureId}/assignment/{assignmentId}")
    public ResponseEntity<SubmitAssignmentResponseDto> getSubmissions(
            @RequestParam String studentNumber,
            @PathVariable Long assignmentId) throws IOException {  // IOException을 처리

        Submission submittedSubmission = submissionQueryService.getSubmissionByUserAndAssignment(studentNumber, assignmentId);

        //log.info("submission.id = {}", submittedSubmission.getId());

        List<SubmissionFile> submittedFiles = submittedSubmission.getFiles();

        // Submission -> SubmissionResponseDto로 변환
        SubmitAssignmentResponseDto responseDto = SubmitAssignmentResponseDto.builder()
                .title(submittedSubmission.getTitle())
                .message(submittedSubmission.getContent())
                .build();

        // DTO를 포함한 응답 반환
        return ResponseEntity.ok(responseDto);
    }

}
