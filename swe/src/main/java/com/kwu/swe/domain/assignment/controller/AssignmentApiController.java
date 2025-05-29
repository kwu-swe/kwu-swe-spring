package com.kwu.swe.domain.assignment.controller;

import com.kwu.swe.domain.assignment.dto.AllAssignmentResponseDto;
import com.kwu.swe.domain.assignment.dto.AssignmentRequestDto;
import com.kwu.swe.domain.assignment.dto.AssignmentResponseDto;
import com.kwu.swe.domain.assignment.dto.AssignmentWithSubmissionResponseDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.service.AssignmentCommandService;
import com.kwu.swe.domain.assignment.service.AssignmentQueryService;
import com.kwu.swe.domain.submission.dto.SubmitAssignmentResponseDto;
import com.kwu.swe.domain.submission.entity.Submission;
import com.kwu.swe.domain.submission.service.SubmissionQueryService;
import com.kwu.swe.domain.user.entity.User;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentApiController {

    private final AssignmentCommandService assignmentService;
    private final AssignmentQueryService assignmentQueryService;
    private final SubmissionQueryService submissionQueryService;

    // 과제 생성
    @PostMapping("/lectures/{lectureId}")
    public ApiResponseDto<String> createAssignment(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestBody AssignmentRequestDto assignmentRequestDto,
                                                   @PathVariable Long lectureId) {
        Long assignmentId = assignmentService.createAssignment(assignmentRequestDto, userDetails.getUsername(), lectureId);

        return ApiResponseDto.onSuccess("과제가 성공적으로 생성되었습니다. 과제 ID: " + assignmentId);
    }

    // 특정 lectureId에 대한 모든 Assignment 조회
    @GetMapping("/lectures/{lectureId}")
    public ApiResponseDto<List<AllAssignmentResponseDto>> getAssignmentsByLectureId(@PathVariable Long lectureId) {
        // lectureId에 해당하는 모든 Assignment 조회
        List<Assignment> assignments = assignmentQueryService.findAssignmentsByLectureId(lectureId);

        // List<Assignment>을 List<AssignmentResponseDto>로 변환
        List<AllAssignmentResponseDto> responseDtos = assignments.stream()
                .map(assignment -> AllAssignmentResponseDto.builder()
                        .assignmentId(assignment.getId())
                        .title(assignment.getTitle())
                        .dueDate(assignment.getDueDate())
                        .build())
                .collect(Collectors.toList());

        return ApiResponseDto.onSuccess(responseDtos);
    }

    /**
     * 1. userdetails 받아오기
     * 2. service return -> submission
     */
    // 특정 assignmentId에 대한 Assignment 조회
    @GetMapping("/{assignmentId}")
    public ApiResponseDto<AssignmentWithSubmissionResponseDto> getAssignmentByLectureIdAndAssignmentId(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long assignmentId) {

        // assignmentId에 해당하는 Assignment 조회
        Assignment assignment = assignmentQueryService.findByAssignmentId(assignmentId);

        Submission submittedSubmission = submissionQueryService.findSubmissionByAssignmentIdAndUserId(assignmentId, userDetails.getUsername());

        // Submission -> SubmissionResponseDto로 변환
        SubmitAssignmentResponseDto submissionResponseDto = SubmitAssignmentResponseDto.builder()
                .submissionId(submittedSubmission.getId())
                .submissionId(submittedSubmission.getId())
                .title(submittedSubmission.getTitle())
                .content(submittedSubmission.getContent())
                .encodedFiles(submittedSubmission.getFiles().stream()
                        .map(submissionFile -> submissionFile.getEncodedURL())
                        .toList())
                .build();

        AssignmentWithSubmissionResponseDto responseDto = AssignmentWithSubmissionResponseDto.builder()
                .assignmentId(assignment.getId())
                .title(assignment.getTitle())
                .content(assignment.getContent())
                .dueDate(assignment.getDueDate())
                .createdAt(assignment.getCreatedAt())
                .encodedFiles(assignment.getFiles().stream()
                        .map(assignmentFile -> assignmentFile.getEncodedURL())
                        .toList())
                .submitAssignmentResponseDto(submissionResponseDto)
                .build();

            return ApiResponseDto.onSuccess(responseDto);
    }

    // 특정 assignmentId에 대한 Assignment 수정
    @PutMapping("/{assignmentId}")
    public ApiResponseDto<Long> updateAssignment(
            @PathVariable Long assignmentId,
            @RequestBody AssignmentRequestDto updatedAssignment) {

        return ApiResponseDto.onSuccess(assignmentService.updateAssignment(assignmentId, updatedAssignment));
    }

    // 특정 assignmentId에 대한 Assignment 삭제
    @DeleteMapping("/{assignmentId}")
    public ApiResponseDto<Void> deleteAssignment(
            @PathVariable Long assignmentId) {

        assignmentService.deleteAssignment(assignmentId);
        return ApiResponseDto.onSuccess(null);
    }
}
