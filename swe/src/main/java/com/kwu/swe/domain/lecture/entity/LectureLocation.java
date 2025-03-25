package com.kwu.swe.domain.lecture.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LectureLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_location_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "location", nullable = false, length = 50)
    private Location location;
}
