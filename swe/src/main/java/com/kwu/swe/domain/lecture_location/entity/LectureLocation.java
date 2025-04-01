package com.kwu.swe.domain.lecture_location.entity;

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

    @Column(name = "location", nullable = false, unique = true)
    private String location;

    private int sizeLimit;
}
