package com.kwu.swe.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LectureSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_schedule_id")
    private Long id;

    //테이블 대신 이넘 연결
    @Enumerated
    @Column(name = "class_time", nullable = false)
    private ClassTime classTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_location_id")
    private LectureLocation lectureLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    public void linkInList(Lecture lecture) {
        lecture.getLectureScheduleList().add(this);
    }

}
