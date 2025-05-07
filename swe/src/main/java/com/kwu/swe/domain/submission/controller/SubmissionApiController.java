package com.kwu.swe.domain.submission.controller;

import com.kwu.swe.domain.submission.dto.SubmissionFileResponseDto;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentRequestDto;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentResponseDto;
import com.kwu.swe.domain.submission.service.FileEncodingCommandServiceImpl;
import com.kwu.swe.domain.submission.service.SubmissionCommandService;
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

    private final FileEncodingCommandServiceImpl fileEncodingService;
    private final SubmissionCommandService submissionCommandService;
    private final FileEncodingCommandServiceImpl fileEncodingCommandServiceImpl;

    @PostMapping(value = "/encode", consumes = "multipart/form-data")
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
    }

    @PostMapping(value = "/{assignmentId}/submit")
    public ResponseEntity<SubmitAssignmentResponseDto> submitAssignment(
            @RequestBody SubmitAssignmentRequestDto submitAssignmentRequestDto){

        try {
            // 1. 과제 제출과 상태 업데이트
            Long submissionId = submissionCommandService.submitSubmissionAndUpdateStatus(
                    submitAssignmentRequestDto.getAssignmentId(),
                    submitAssignmentRequestDto.getTitle(),
                    submitAssignmentRequestDto.getContent());

            // 2. 파일 인코딩 및 DB에 저장
            for (int i = 0; i < submitAssignmentRequestDto.getFileNames().size(); i++) {
                String encodedResult = submitAssignmentRequestDto.getEncodedFiles().get(i); // 인코딩된 결과
                String fileName = submitAssignmentRequestDto.getFileNames().get(i); // 파일 이름 (추가로 파일 이름 리스트가 있어야 함)

                // DB에 저장
                fileEncodingService.saveFileToDB(submissionId, fileName, encodedResult);
            }
            // 성공 응답 반환
            SubmitAssignmentResponseDto responseDto = SubmitAssignmentResponseDto.builder()
                    .message("과제가 성공적으로 제출되었습니다.")
                    .success(true)
                    .submissionId(submissionId)
                    .build();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseDto);

        } catch (IOException e) {
            // 오류 처리
            SubmitAssignmentResponseDto responseDto = SubmitAssignmentResponseDto.builder()
                    .message("파일 인코딩 처리 중 오류가 발생했습니다.")
                    .success(false)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDto);
        }
    }
}