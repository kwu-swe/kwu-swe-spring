package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.user.entity.Grade;
import com.kwu.swe.domain.user.entity.LectureStudent;

import java.util.List;

public interface LectureQueryService {

    List<Lecture> getAllLectures();

    List<Lecture> getStudentLectures(String code);

    List<LectureStudent> getGradesOfLecture(String code, Long lectureId);

    LectureStudent getStudentGrade(String code, Long lectureId);
}
