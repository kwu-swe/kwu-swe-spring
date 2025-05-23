package com.kwu.swe.domain.assignment.controller;

import com.kwu.swe.domain.assignment.dto.AllAssignmentResponseDto;
import com.kwu.swe.domain.assignment.dto.AssignmentRequestDto;
import com.kwu.swe.domain.assignment.dto.AssignmentResponseDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.service.AssignmentCommandService;
import com.kwu.swe.domain.assignment.service.AssignmentQueryService;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentApiController {

    private final AssignmentCommandService assignmentService;
    private final AssignmentQueryService assignmentQueryService;

    // 과제 생성
    @PostMapping("/lectures/{lectureId}")
    public ApiResponseDto<String> createAssignment(@RequestBody AssignmentRequestDto assignmentRequestDto,
                                                   @PathVariable Long lectureId) {
        Long assignmentId = assignmentService.createAssignment(assignmentRequestDto, lectureId);

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


    // 특정 assignmentId에 대한 Assignment 조회
    @GetMapping("/{assignmentId}")
    public ApiResponseDto<AssignmentResponseDto> getAssignmentByLectureIdAndAssignmentId(
            @PathVariable Long assignmentId) {

        // assignmentId에 해당하는 Assignment 조회
            Assignment assignment = assignmentQueryService.findByAssignmentId(assignmentId);

        AssignmentResponseDto responseDto = AssignmentResponseDto.builder()
                .title(assignment.getTitle())
                .content(assignment.getContent())
                .dueDate(assignment.getDueDate())
                .encodedFiles(assignment.getFiles().stream()
                        .map(assignmentFile -> assignmentFile.getEncodedURL())
                        .toList())
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
