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
    public ApiResponseDto<Long> registerLecture(@RequestParam String professorNumber,
                                                @RequestBody RegisterLectureRequestDto dto) {
        return ApiResponseDto.onSuccess(lectureCommandService.registerLecture(professorNumber, dto));
    }

    @GetMapping
    public ApiResponseDto<List<LectureResponseDto>> getAllLecture(){
        List<Lecture> allLectures = lectureQueryService.getAllLectures();
        return ApiResponseDto.onSuccess(getLectureResponseDtos(allLectures));
    }

    @GetMapping("/students")
    public ApiResponseDto<List<LectureResponseDto>> getStudentLectureInfo(@RequestParam String studentNumber) {
        List<Lecture> studentLectures = lectureQueryService.getStudentLectures(studentNumber);
        return ApiResponseDto.onSuccess(getLectureResponseDtos(studentLectures));
    }

    @PostMapping("/{lectureId}")
    public ApiResponseDto<Long> registerCourse(@PathVariable Long lectureId,
                                               @RequestParam String studentNumber) {
        return ApiResponseDto.onSuccess(
                lectureCommandService.registerCourse(
                        studentNumber,
                        lectureId));
    }

    @PostMapping("/{lectureId}/assistants/{assistantNumber}")
    public ApiResponseDto<Long> registerAssistant(@PathVariable Long lectureId,
                                                  @PathVariable String assistantNumber,
                                                  @RequestParam String professorNumber) {
        return ApiResponseDto.onSuccess(
                lectureCommandService.registerAssistantOfLecture(
                        professorNumber,
                        assistantNumber,
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
                                .studentNumber(lecture.getProfessor().getStudentNumber())
                                .phoneNumber(lecture.getProfessor().getPhoneNumber())
                                .build())
                        .lectureStatus(lecture.getLectureStatus())
                        .year(lecture.getYear())
                        .sizeLimit(lecture.getSizeLimit())
                        .courseResponseDto(CourseResponseDto.builder()
                                .courseId(lecture.getCourse().getId())
                                .courseName(lecture.getCourse().getCourseName())
                                .courseNumber(lecture.getCourse().getCourseNumber())
                                .score(lecture.getCourse().getScore())
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
