package com.kwu.swe.domain.assignment.controller;

import com.kwu.swe.domain.assignment.dto.AssignmentRequestDto;
import com.kwu.swe.domain.assignment.dto.AssignmentResponseDto;
import com.kwu.swe.domain.assignment.entity.Assignment;
import com.kwu.swe.domain.assignment.service.AssignmentCommandService;
import com.kwu.swe.domain.assignment.service.AssignmentQueryService;
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
    private final AssignmentCommandService assignmentCommandService;

    // 과제 생성
    @PostMapping("/lecture/{lectureId}")
    public ResponseEntity<String> createAssignment(@RequestBody AssignmentRequestDto assignmentRequestDto,
                                                   @PathVariable Long lectureId) {
        Long assignmentId = assignmentService.createAssignment(assignmentRequestDto, lectureId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("과제가 성공적으로 생성되었습니다. 과제 ID: " + assignmentId);
    }

    // 특정 lectureId에 대한 모든 Assignment 조회
    @GetMapping("/lecture/{lectureId}")
    public ResponseEntity<List<AssignmentResponseDto>> getAssignmentsByLectureId(@PathVariable Long lectureId) {
        // lectureId에 해당하는 모든 Assignment 조회
        List<Assignment> assignments = assignmentQueryService.findAssignmentsByLectureId(lectureId);

        // 과제가 없으면 404 반환
        if (assignments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // List<Assignment>을 List<AssignmentResponseDto>로 변환
        List<AssignmentResponseDto> responseDtos = assignments.stream()
                .map(assignment -> AssignmentResponseDto.builder()
                        .title(assignment.getTitle())
                        .content(assignment.getContent())
                        .encodedFiles(assignment.getFiles().stream()
                                .map(assignmentFile -> assignmentFile.getEncodedURL()).toList())
                        .build())
                .collect(Collectors.toList());

        // 과제가 있으면 200 OK 반환
        return ResponseEntity.ok(responseDtos);
    }


    // 특정 lectureId와 assignmentId에 대한 Assignment 조회
    @GetMapping("/lecture/{lectureId}/assignment/{assignmentId}")
    public ResponseEntity<AssignmentResponseDto> getAssignmentByLectureIdAndAssignmentId(
            @PathVariable Long lectureId,
            @PathVariable Long assignmentId) {

        // lectureId와 assignmentId에 해당하는 Assignment 조회
        try {
            Assignment assignment = assignmentQueryService.getAssignmentByLectureIdAndAssignmentId(lectureId, assignmentId);

            AssignmentResponseDto responseDto = AssignmentResponseDto.builder()
                    .title(assignment.getTitle())
                    .content(assignment.getContent())
                    .build();

            return ResponseEntity.ok(responseDto);  // 조회된 과제를 200 OK로 반환
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();  // 과제가 없으면 404 Not Found 반환
        }
    }

    // 특정 assignmentId에 대한 Assignment 수정
    @PutMapping("/lecture/{lectureId}/assignment/{assignmentId}")
    public ResponseEntity<AssignmentResponseDto> updateAssignment(
            @PathVariable Long lectureId,
            @PathVariable Long assignmentId,
            @RequestBody AssignmentRequestDto updatedAssignment) {

        try {
            // Assignment 수정
            Long assignment = assignmentService.updateAssignment(lectureId, assignmentId, updatedAssignment);

            Assignment revisedAssignment = assignmentQueryService.getAssignmentByLectureIdAndAssignmentId(lectureId, assignmentId);

            AssignmentResponseDto responseDto = AssignmentResponseDto.builder()
                    .title(revisedAssignment.getTitle())
                    .content(revisedAssignment.getContent())
                    .encodedFiles(revisedAssignment.getFiles().stream()
                            .map(assignmentFile -> assignmentFile.getEncodedURL()).toList())
                    .build();

            return ResponseEntity.ok(responseDto); // 수정된 과제 반환
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 과제가 없으면 404 반환
        }
    }

    // 특정 lectureId와 assignmentId에 대한 Assignment 삭제
    @DeleteMapping("/lecture/{lectureId}/assignment/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(
            @PathVariable Long lectureId,
            @PathVariable Long assignmentId) {

        try {
            // Assignment 삭제
            assignmentService.deleteAssignment(lectureId, assignmentId);
            return ResponseEntity.noContent().build(); // 삭제 성공 시 204 반환
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 과제가 없으면 404 반환
        }
    }
}
