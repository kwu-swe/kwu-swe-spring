package com.kwu.swe.domain.user.repository;

import com.kwu.swe.domain.lecture.entity.Lecture;
import com.kwu.swe.domain.user.entity.LectureStudent;
import com.kwu.swe.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureStudentRepository extends JpaRepository<LectureStudent, Long> {
    List<LectureStudent> findByStudent(User student);

    boolean existsByLectureAndStudent(Lecture lecture, User user);

    Optional<LectureStudent> findByStudentIdAndLectureId(Long studentId, Long lectureId);

    List<LectureStudent> findByLectureProfessorId(Long professorId);

    List<LectureStudent> findByLecture(Lecture lecture);
}
