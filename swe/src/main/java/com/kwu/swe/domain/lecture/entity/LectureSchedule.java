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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_period_id")
    private LecturePeriod lecturePeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_location_id")
    private LectureLocation lectureLocation;
}
