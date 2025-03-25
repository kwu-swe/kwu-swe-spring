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

    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_status", nullable = false)
    private LectureStatus lectureStatus;

    @Enumerated
    @Column(name = "semester", nullable = false, length = 50)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_schedule_id")
    private LectureSchedule lectureSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
}
