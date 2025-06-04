package com.kwu.swe.domain.user.entity;

import com.kwu.swe.domain.auditing.entity.BaseTimeEntity;
import com.kwu.swe.domain.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "lecture_student", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student", "lecture"})
})
public class LectureStudent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_student_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture")
    private Lecture lecture;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    @Builder.Default
    private Grade grade = Grade.IN_PROGRESS;

    public void assignGrade(Grade grade) {
        this.grade = grade;
    }
}
