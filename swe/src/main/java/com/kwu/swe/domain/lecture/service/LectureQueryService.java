package com.kwu.swe.domain.lecture.service;

import com.kwu.swe.domain.lecture.entity.Lecture;

import java.util.List;

public interface LectureQueryService {

    List<Lecture> getAllLectures();
}
