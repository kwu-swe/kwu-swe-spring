package com.kwu.swe.domain.lecture.entity;

import com.kwu.swe.domain.course.entity.Course;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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

    private Year year;

    //강의 전, 강의 중, 강의 후
    //학점 부여를 위한 status
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_status", nullable = false)
    private LectureStatus lectureStatus;

    //4개의 학기밖에 없으므로 entity -> enum으로 변경
    @Enumerated
    @Column(name = "semester", nullable = false, length = 50)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    //강의 교수 등록
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "professor_id")
//    private User professor;

    @Builder.Default
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<LectureSchedule> lectureScheduleList = new ArrayList<>();

}
