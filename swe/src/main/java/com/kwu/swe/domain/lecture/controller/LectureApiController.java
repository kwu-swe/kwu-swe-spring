package com.kwu.swe.domain.lecture.controller;

import com.kwu.swe.domain.course.dto.response.CourseResponseDto;
import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;
import com.kwu.swe.domain.lecture.dto.response.LectureResponseDto;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.service.LectureCommandService;
import com.kwu.swe.domain.lecture.service.LectureQueryService;
import com.kwu.swe.domain.user.dto.UserResponseDto;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureApiController {

    private final LectureCommandService lectureCommandService;
    private final LectureQueryService lectureQueryService;

    @PostMapping
    public ApiResponseDto<Long> registerLecture(@RequestParam String code,
                                                @RequestBody RegisterLectureRequestDto dto) {
        return ApiResponseDto.onSuccess(lectureCommandService.registerLecture(code, dto));
    }

    @GetMapping
    public ApiResponseDto<List<LectureResponseDto>> getAllLecture(){
        List<Lecture> allLectures = lectureQueryService.getAllLectures();
        return ApiResponseDto.onSuccess(getLectureResponseDtos(allLectures));
    }

    @GetMapping("/students")
    public ApiResponseDto<List<LectureResponseDto>> getStudentLectureInfo(@RequestParam String code) {
        List<Lecture> studentLectures = lectureQueryService.getStudentLectures(code);
        return ApiResponseDto.onSuccess(getLectureResponseDtos(studentLectures));
    }

    @PostMapping("/{lectureId}")
    public ApiResponseDto<Long> registerCourse(@PathVariable Long lectureId,
                                               @RequestParam String code) {
        return ApiResponseDto.onSuccess(
                lectureCommandService.registerCourse(
                        code,
                        lectureId));
    }

    @PostMapping("/{lectureId}/assistants/{assistantCode}")
    public ApiResponseDto<Long> registerAssistant(@PathVariable Long lectureId,
                                                  @PathVariable String assistantCode,
                                                  @RequestParam String professorCode) {
        return ApiResponseDto.onSuccess(
                lectureCommandService.registerAssistantOfLecture(
                        professorCode,
                        assistantCode,
                        lectureId
                )
        );
    }

    private static List<LectureResponseDto> getLectureResponseDtos(List<Lecture> allLectures) {
        List<LectureResponseDto> result = allLectures.stream()
                .map(lecture -> LectureResponseDto.builder()
                        .semester(lecture.getSemester())
                        .professor(UserResponseDto.builder()
                                .role(lecture.getProfessor().getRole())
                                .name(lecture.getProfessor().getName())
                                .code(lecture.getProfessor().getCode())
                                .phoneNumber(lecture.getProfessor().getPhoneNumber())
                                .createdAt(lecture.getProfessor().getCreatedAt())
                                .build())
                        .lectureStatus(lecture.getLectureStatus())
                        .year(lecture.getYear().getValue())
                        .sizeLimit(lecture.getSizeLimit())
                        .createdAt(lecture.getCreatedAt())
                        .courseResponseDto(CourseResponseDto.builder()
                                .courseId(lecture.getCourse().getId())
                                .courseName(lecture.getCourse().getCourseName())
                                .courseNumber(lecture.getCourse().getCourseNumber())
                                .score(lecture.getCourse().getScore())
                                .createdAt(lecture.getCourse().getCreatedAt())
                                .build())
                        .lectureScheduleAndLocation(
                                lecture.getLectureScheduleList().stream()
                                        .map(schedule -> Map.of(
                                                "classTime", schedule.getClassTime().getKey(), // 예: "MON_1"
                                                "location", schedule.getLectureLocation().getLocation() // 예: "공학관 101호"
                                        ))
                                        .collect(Collectors.toList())
                        )
                        .build()).toList();
        return result;
    }
}
