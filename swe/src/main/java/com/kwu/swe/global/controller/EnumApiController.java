package com.kwu.swe.global.controller;

import com.kwu.swe.domain.course.entity.CourseType;
import com.kwu.swe.domain.lecture.entity.LectureStatus;
import com.kwu.swe.domain.lecture.entity.Semester;
import com.kwu.swe.domain.lecture_schedule.entity.ClassTime;
import com.kwu.swe.domain.submission.entity.SubmissionStatus;
import com.kwu.swe.domain.user.entity.Grade;
import com.kwu.swe.domain.user.entity.Role;
import com.kwu.swe.global.util.EnumConvertUtil;
import com.kwu.swe.presentation.payload.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enums")
@RequiredArgsConstructor
public class EnumApiController {

    @GetMapping("/course-type")
    public ApiResponseDto<List<Map<String, String>>> getAllCourseType() {
        return ApiResponseDto.onSuccess(EnumConvertUtil.getEnumList(CourseType.class));
    }

    @GetMapping("/lecture-status")
    public ApiResponseDto<List<Map<String, String>>> getAllLectureStatus() {
        return ApiResponseDto.onSuccess(EnumConvertUtil.getEnumList(LectureStatus.class));
    }
    @GetMapping("/semester")
    public ApiResponseDto<List<Map<String, String>>> getAllSemester() {
        return ApiResponseDto.onSuccess(EnumConvertUtil.getEnumList(Semester.class));
    }
    @GetMapping("/class-time")
    public ApiResponseDto<List<Map<String, String>>> getAllClassTime() {
        return ApiResponseDto.onSuccess(EnumConvertUtil.getEnumList(ClassTime.class));
    }
    @GetMapping("/submission-status")
    public ApiResponseDto<List<Map<String, String>>> getAllSubmissionStatus() {
        return ApiResponseDto.onSuccess(EnumConvertUtil.getEnumList(SubmissionStatus.class));
    }
    @GetMapping("/grade")
    public ApiResponseDto<List<Map<String, String>>> getAllGrade() {
        return ApiResponseDto.onSuccess(EnumConvertUtil.getEnumList(Grade.class));
    }

    @GetMapping("/role")
    public ApiResponseDto<List<Map<String, String>>> getAllRole() {
        return ApiResponseDto.onSuccess(EnumConvertUtil.getEnumList(Role.class));
    }
}
