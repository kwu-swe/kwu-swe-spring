package com.kwu.swe.domain.lecture.entity;

import com.kwu.swe.domain.course.entity.Course;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    private int sizeLimit;

    //강의 전, 강의 중, 강의 후
    //학점 부여를 위한 status
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_status", nullable = false)
    private LectureStatus lectureStatus;

    //4개의 학기밖에 없으므로 entity -> enum으로 변경
    @Enumerated
    @Column(name = "semester", nullable = false, length = 50)
    private Semester semester;

    //해당 강의의 스케줄 및 장소를 여러개 정할 수 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_schedule_id")
    private LectureSchedule lectureSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
