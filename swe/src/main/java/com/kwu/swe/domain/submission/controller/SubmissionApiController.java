package com.kwu.swe.domain.submission.controller;

import com.kwu.swe.domain.assignment.repository.AssignmentRepository;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentRequestDto;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentResponseDto;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.service.SubmissionCommandService;
import com.kwu.swe.domain.submission.service.SubmissionQueryService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionApiController {

    private final SubmissionCommandService submissionCommandService;
    private final SubmissionQueryService submissionQueryService;
    private final AssignmentRepository assignmentRepository;

    @PostMapping(value = "/assignments/{assignmentId}")
    public ApiResponseDto<String> submitAssignment(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long assignmentId,
            @RequestBody SubmitAssignmentRequestDto submitAssignmentRequestDto){

        Long submissionId = submissionCommandService.submitSubmissionAndUpdateStatus(assignmentId, userDetails.getUsername(), submitAssignmentRequestDto);

        return ApiResponseDto.onSuccess("과제가 성공적으로 제출되었습니다. 과제 ID: " + submissionId);
    }

    @GetMapping("/assignments/{assignmentId}")
    public ApiResponseDto<SubmitAssignmentResponseDto> getSubmissions(
            @PathVariable Long assignmentId,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) throws IOException {  // IOException을 처리

        Optional<Submission> submission = submissionQueryService.findSubmissionByAssignmentIdAndUserId(assignmentId, userDetails.getUsername());

        // Submission -> SubmissionResponseDto로 변환
        SubmitAssignmentResponseDto responseDto = SubmitAssignmentResponseDto.builder()
                .submissionId(submission.get().getId())
                .title(submission.get().getTitle())
                .content(submission.get().getContent())
                .encodedFiles(submission.get().getFiles().stream()
                        .map(submissionFile -> submissionFile.getEncodedURL())
                        .toList())
                .build();

        // DTO를 포함한 응답 반환
        return ApiResponseDto.onSuccess(responseDto);
    }

    // 특정 submissionId에 대한 Submission 수정
    @PutMapping("/{submissionId}")
    public ApiResponseDto<Long> updateSubmission(
            @PathVariable Long submissionId,
            @RequestBody SubmitAssignmentRequestDto submitAssignmentRequestDto) {

        return ApiResponseDto.onSuccess(submissionCommandService.updateSubmission(submissionId, submitAssignmentRequestDto));
    }

    // 특정 submissionId에 대한 Submission 삭제
    @DeleteMapping("/{submissionId}")
    public ApiResponseDto<Void> deleteSubmission(
            @PathVariable Long submissionId) {

        submissionCommandService.deleteSubmission(submissionId);
        return ApiResponseDto.onSuccess(null);
    }
}
