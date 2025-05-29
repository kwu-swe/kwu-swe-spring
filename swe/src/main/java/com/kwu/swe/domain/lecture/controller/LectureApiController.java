package com.kwu.swe.domain.lecture.controller;

import com.kwu.swe.domain.course.dto.response.CourseResponseDto;
import com.kwu.swe.domain.lecture.dto.request.RegisterLectureRequestDto;
import com.kwu.swe.domain.lecture.dto.response.LectureResponseDto;
import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.lecture.service.LectureCommandService;
import com.kwu.swe.domain.lecture.service.LectureQueryService;
import com.kwu.swe.domain.user.dto.UserResponseDto;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ApiResponseDto<Long> registerLecture(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
                                                @RequestBody RegisterLectureRequestDto dto) {
        return ApiResponseDto.onSuccess(lectureCommandService.registerLecture(userDetails.getUsername(), dto));
    }

    @GetMapping
    public ApiResponseDto<List<LectureResponseDto>> getAllLecture(){
        List<Lecture> allLectures = lectureQueryService.getAllLectures();
        return ApiResponseDto.onSuccess(getLectureResponseDtos(allLectures));
    }

    @GetMapping("/students")
    public ApiResponseDto<List<LectureResponseDto>> getStudentLectureInfo(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<Lecture> studentLectures = lectureQueryService.getStudentLectures(userDetails.getUsername());
        return ApiResponseDto.onSuccess(getLectureResponseDtos(studentLectures));
    }

    @PostMapping("/{lectureId}")
    public ApiResponseDto<Long> registerCourse(@PathVariable Long lectureId,
                                               @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return ApiResponseDto.onSuccess(
                lectureCommandService.registerCourse(
                        userDetails.getUsername(),
                        lectureId));
    }

//    @PostMapping("/{lectureId}/assistants/{assistantCode}")
//    public ApiResponseDto<Long> registerAssistant(@PathVariable Long lectureId,
//                                                  @PathVariable String assistantCode,
//                                                  @RequestParam String professorCode) {
//        return ApiResponseDto.onSuccess(
//                lectureCommandService.registerAssistantOfLecture(
//                        professorCode,
//                        assistantCode,
//                        lectureId
//                )
//        );
//    }

    private static List<LectureResponseDto> getLectureResponseDtos(List<Lecture> allLectures) {
        List<LectureResponseDto> result = allLectures.stream()
                .map(lecture -> LectureResponseDto.builder()
                        .lectureId(lecture.getId())
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
                        .lectureTimeAndLocation(
                                lecture.getLectureScheduleList().stream()
                                        .collect(Collectors.toMap(
                                                schedule -> schedule.getClassTime().getKey(),      // key
                                                schedule -> schedule.getLectureLocation().getId()  // value
                                        ))
                        )
                        .build()).toList();
        return result;
    }
}
