package com.kwu.swe.domain.lecture_location.entity;

import com.kwu.swe.domain.auditing.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LectureLocation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_location_id")
    private Long id;

    @Column(name = "location", nullable = false, unique = true)
    private String location;

    private int sizeLimit;
}
