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

import java.util.List;

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
                        .build()).toList();
        return ApiResponseDto.onSuccess(result);
    }
}
