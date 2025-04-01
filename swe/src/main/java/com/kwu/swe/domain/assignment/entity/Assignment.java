package com.kwu.swe.domain.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;  // 제출 기한 추가

    // 기한이 지나지 않았는지 확인하는 메서드
    public boolean isSubmittedOnTime(LocalDateTime submittedAt) {
        return !submittedAt.isAfter(dueDate);  // 제출 시간이 기한 이후인지 체크
    }
}
