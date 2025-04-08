package com.kwu.swe.domain.lecture.repository;

import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.user.entity.LectureStudent;
import com.kwu.swe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
