package com.kwu.swe.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LecturePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_period_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_time", nullable = false, length = 50)
    private ClassTime classTime;
}
